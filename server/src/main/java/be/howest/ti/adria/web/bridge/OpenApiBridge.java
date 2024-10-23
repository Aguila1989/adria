package be.howest.ti.adria.web.bridge;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.howest.ti.adria.logic.controller.Controller;
import be.howest.ti.adria.logic.controller.DefaultController;
import be.howest.ti.adria.logic.domain.DiagnoseLocation;
import be.howest.ti.adria.logic.domain.Diagnosis;
import be.howest.ti.adria.logic.domain.MedicalHistoryRecord;
import be.howest.ti.adria.logic.domain.User;
import be.howest.ti.adria.logic.domain.enums.Gender;
import be.howest.ti.adria.logic.exceptions.RepositoryException;
import be.howest.ti.adria.web.HttpResponseCode;
import be.howest.ti.adria.web.exceptions.IllegalRequestException;
import be.howest.ti.adria.web.exceptions.MalformedRequestException;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.BodyProcessorException;
import io.vertx.ext.web.validation.ParameterProcessorException;
import io.vertx.ext.web.validation.RequestPredicateException;


/**
* In the AdriaOpenApiBridge class you will create one handler-method per API operation.
* The job of the "bridge" is to bridge between JSON (request and response) and Java (the controller).
* <p>
* For each API operation you should get the required data from the `Request` class.
* The Request class will turn the HTTP request data into the desired Java types (int, String, Custom class,...)
* This desired type is then passed to the controller.
* The return value of the controller is turned to Json or another Web data type in the `Response` class.
*/
public class OpenApiBridge {
    private static final Logger LOGGER = Logger.getLogger(OpenApiBridge.class.getName());
    private final Controller controller;

    public Router buildRouter(RouterBuilder routerBuilder) {
        LOGGER.log(Level.INFO, "Installing cors handlers");
        routerBuilder.rootHandler(createCorsHandler());

        LOGGER.log(Level.INFO, "Installing handler for : getAllSubscriptionTypes");
        routerBuilder.operation("getSubscriptions").handler(this::getAllSubscriptionTypes);

        LOGGER.log(Level.INFO, "Installing handler for : updateUserSubscription");
        routerBuilder.operation("updateSubscription").handler(this::updateUserSubscription);

        LOGGER.log(Level.INFO, "Installing handler for : createUser");
        routerBuilder.operation("createUser").handler(this::createUser);

        LOGGER.log(Level.INFO, "Installing handler for : getUserInfoByID");
        routerBuilder.operation("getUserInfo").handler(this::getUserInfoByIDRequest);

        LOGGER.log(Level.INFO, "Installing handler for : getUserMedicalEntry");
        routerBuilder.operation("getUserMedicalEntry").handler(this::getUserMedicalEntry);

        LOGGER.log(Level.INFO, "Installing handler for : getColonies");
        routerBuilder.operation("getColonies").handler(this::getAllColonies);

        LOGGER.log(Level.INFO, "Installing handler for : addMetrics");
        routerBuilder.operation("addMetrics").handler(this::addMetrics);

        LOGGER.log(Level.INFO, "installing handler for : getMetrics");
        routerBuilder.operation("getMetrics").handler(this::getMetrics);

        LOGGER.log(Level.INFO, "installing handler for : addDiagnosis");
        routerBuilder.operation("addDiagnosis").handler(this::addDiagnosis);

        LOGGER.log(Level.INFO, "installing handler for : getSymptoms");
        routerBuilder.operation("getSymptoms").handler(this::getSymptoms);

        LOGGER.log(Level.INFO, "installing handler for : createAiDiagnosis");
        routerBuilder.operation("createAiDiagnosis").handler(this::createAiDiagnosis);

        LOGGER.log(Level.INFO, "Installing failure handlers for all operations");
        routerBuilder.operations().forEach(op -> op.failureHandler(this::onFailedRequest));

        LOGGER.log(Level.INFO, "All handlers are installed, creating router.");

        return routerBuilder.createRouter();
    }

    public OpenApiBridge() {
        this.controller = new DefaultController();
    }

    public OpenApiBridge(Controller controller) {
        this.controller = controller;
    }

    private void onFailedRequest(RoutingContext ctx) {
        Throwable cause = ctx.failure();
        int code = ctx.statusCode();

        // Map custom runtime exceptions to a HTTP status code.
        LOGGER.log(Level.INFO, "Failed request", cause);
        if (cause instanceof IllegalArgumentException || cause instanceof MalformedRequestException) {
            code = HttpResponseCode.BAD_REQUEST;
        } else if (cause instanceof NoSuchElementException) {
            code = HttpResponseCode.NOT_FOUND;
        } else if (cause instanceof IllegalRequestException) {
            if (((IllegalRequestException) cause).hasErrorArray()) {
                Response.sendMultiFailure(ctx, HttpResponseCode.BAD_REQUEST, ((IllegalRequestException) cause).getErrorArray());
            } else {
                Response.sendFailure(ctx, HttpResponseCode.BAD_REQUEST, cause.getMessage());
            }
            return;
        } else if (cause instanceof BodyProcessorException ||
                    cause instanceof RepositoryException ||
                    cause instanceof RequestPredicateException) {
            Response.sendFailure(ctx, HttpResponseCode.BAD_REQUEST, cause.getMessage());
            return;
        } else if (cause instanceof ParameterProcessorException) {
            Response.sendFailure(ctx, HttpResponseCode.BAD_REQUEST, "invalid request url");
            return;
        }
        else {
            LOGGER.log(Level.WARNING, "Failed request", cause);
        }

        Response.sendFailure(ctx, code, ")-: Oops, something went wrong. Its not you, its us.");
    }

    public void getUserInfoByIDRequest(RoutingContext ctx) {
        Request request = Request.from(ctx);
        int adriaID = request.getAdriaId();
        int includeMedicalHistory = request.getBoolPathParam("includeMedicalHistory");

        JsonObject response = controller.getUserInfoByID(adriaID);
        if (includeMedicalHistory == 1) {
            List<JsonObject> array = controller.getMedicalHistoryByID(adriaID);
            response.put("medicalHistory", array);
        }

        Response.sendOkJsonResponse(ctx, response);
    }

    public void getUserMedicalEntry(RoutingContext ctx) {
        Request request = Request.from(ctx);
        int adriaId = request.getAdriaId();
        int entryId = request.getEntryId() == -1 ? controller.getLastMedicalEntryId(adriaId) : request.getEntryId();

        JsonObject response = controller.getMedicalHistoryEntry(adriaId, entryId);

        if (response == null) {
            Response.sendFailure(ctx, HttpResponseCode.NOT_FOUND, "entry not found");
        } else {
            Response.sendOkJsonResponse(ctx, response);
        }
    }

    public void getAllSubscriptionTypes(RoutingContext ctx) {
        Response.sendOkJsonResponse(ctx, controller.getSubscriptionTypes());
    }

    public void updateUserSubscription(RoutingContext ctx) {
        Request request = Request.from(ctx);

        int newSubsciptionID = Integer.parseInt(request.getPartOfDataFromBody("newSubscriptionType"));
        int userID = request.getAdriaId();
        controller.updateSubscriptions(newSubsciptionID, userID);

        Response.sendSuccessfullUpdate(ctx);
    }

    public void createUser(RoutingContext ctx) {
        Request rs = Request.from(ctx);
        long id = Long.parseLong(rs.getPartOfDataFromBody("adriaId"));
        String firstName = rs.getPartOfDataFromBody("firstname");
        String lastName = rs.getPartOfDataFromBody("lastname");
        String birthDate = rs.getPartOfDataFromBody("birthdate");
        String gender = rs.getPartOfDataFromBody("gender");
        int colonyID = Integer.parseInt(rs.getPartOfDataFromBody("colonyId"));
        int bloodTypeID = controller.getBloodGroupID(rs.getPartOfDataFromBody("bloodType"));

        User user = new User(id, firstName, lastName, Date.valueOf(birthDate), Gender.getGender(gender), bloodTypeID, colonyID);
        controller.createUser(user);

        Response.sendCreatedResponse(ctx);
    }

    private void getAllColonies(RoutingContext ctx) {
        JsonObject res = controller.getAllColonies();
        Response.sendOkJsonResponse(ctx, res);
    }

    private void addMetrics(RoutingContext ctx) {
        Request rs = Request.from(ctx);
        JsonArray data;
        try {
            data = rs.getBody().getJsonArray("data");
        } catch (IllegalStateException ex) {
            LOGGER.log(Level.WARNING, "tried getting body as json, but was not able to", ex);
            throw new IllegalRequestException("body must be json");
        }
        controller.addMetrics(rs.getAdriaId(), data);
        Response.sendCreatedResponse(ctx);
    }

    private void getMetrics(RoutingContext ctx) {
        Request rs = Request.from(ctx);
        int adriaId = rs.getAdriaId();
        String startDate = !rs.getStringPathParam("startDate").isEmpty() ? rs.getStringPathParam("startDate") : null;
        String endDate = !rs.getStringPathParam("endDate").isEmpty() ? rs.getStringPathParam("endDate") : null;
        JsonObject summary = controller.getMetrics(adriaId, startDate, endDate);
        Response.sendOkJsonResponse(ctx, summary);
    }

    private void addDiagnosis(RoutingContext ctx) {
        Request rs = Request.from(ctx);
        int adriaId = rs.getAdriaId();

        //body to json
        JsonObject data;
        try {
            data = rs.getBody().getJsonObject("data");
        } catch (IllegalStateException ex) {
            LOGGER.log(Level.WARNING, "tried getting body as json, but was not able to", ex);
            throw new IllegalRequestException("body must be json");
        }

        //getting date and time
        Date sqlDate = null;
        Time sqlTime = null;
        try {
            sqlDate = Date.valueOf(data.getString("date"));
            sqlTime = Time.valueOf(data.getString("time"));
        } catch (IllegalArgumentException ex) {
            throw new IllegalRequestException("date and or time was invalid");
        }

        //location
        DiagnoseLocation location;
        try {
            location = new DiagnoseLocation(data.getJsonObject("location"));
        } catch (IllegalArgumentException e) {
            throw new IllegalRequestException("the provided location was invalid");
        }

        //diagnosedBy
        JsonArray diagnosedBy = data.getJsonArray("diagnosedBy");
        int[] parsedDiagnodesBy = diagnosedBy.stream().mapToInt(id -> Integer.parseInt(id.toString())).toArray();

        //symptoms
        JsonArray symptoms = data.getJsonObject("diagnosis").getJsonArray("symptoms");
        List<String> parsedSymptoms = symptoms.stream().map(Object::toString).toList();

        //conditions
        String condition = data.getJsonObject("diagnosis").getString("condition"); //TO DO: add proper support for object

        //using the parsed stuff
        Diagnosis diag = new Diagnosis(parsedSymptoms.toArray(new String[0]), condition);
        MedicalHistoryRecord newRecord = new MedicalHistoryRecord(adriaId, sqlDate, sqlTime, parsedDiagnodesBy, diag);
        newRecord.setLocation(location);

        int entryId = controller.addMedicalHistoryEntry(newRecord);
        Response.sendOkJsonResponse(ctx, new JsonObject().put("entryId", entryId));
    }

    private CorsHandler createCorsHandler() {
        return CorsHandler.create(".*.")
        .allowedHeader("x-requested-with")
        .allowedHeader("Access-Control-Allow-Origin")
        .allowedHeader("Access-Control-Allow-Credentials")
        .allowCredentials(true)
        .allowedHeader("origin")
        .allowedHeader("Content-Type")
        .allowedHeader("Authorization")
        .allowedHeader("accept")
        .allowedMethod(HttpMethod.HEAD)
        .allowedMethod(HttpMethod.GET)
        .allowedMethod(HttpMethod.POST)
        .allowedMethod(HttpMethod.OPTIONS)
        .allowedMethod(HttpMethod.PATCH)
        .allowedMethod(HttpMethod.DELETE)
        .allowedMethod(HttpMethod.PUT);
    }

    private void getSymptoms(RoutingContext ctx) {
        Response.sendOkJsonResponse(ctx, controller.getSymptoms());
    }

    private void createAiDiagnosis(RoutingContext ctx) {
        Request rs = Request.from(ctx);
        Response.sendOkJsonResponse(ctx, controller.getDiagnose(rs.getBody().getJsonArray("symptoms")));
    }
}
