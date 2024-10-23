package be.howest.ti.adria.web.bridge;

import be.howest.ti.adria.web.exceptions.MalformedRequestException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Request class is responsible for translating information that is part of the
 * request into Java.
 * For every piece of information that you need from the request, you should provide a method here.
 * You can find information in:
 * - the request path: params.pathParameter("some-param-name")
 * - the query-string: params.queryParameter("some-param-name")
 * Both return a `RequestParameter`, which can contain a string or an integer in our case.
 * The actual data can be retrieved using `getInteger()` or `getString()`, respectively.
 * You can check if it is an integer (or not) using `isNumber()`.
 * Finally, some requests have a body. If present, the body will always be in the json format.
 * You can acces this body using: `params.body().getJsonObject()`.
 * **TIP:** Make sure that al your methods have a unique name. For instance, there is a request
 * that consists of more than one "player name". You cannot use the method `getPlayerName()` for both,
 * you will need a second one with a different name.
 */
public class Request {
    private static final Logger LOGGER = Logger.getLogger(Request.class.getName());
    private static final String PARAM_NOT_FOUND_MSG = "Parameter not found.";

    private final RequestParameters params;

    public static Request from(RoutingContext ctx) {
        return new Request(ctx);
    }

    protected Request(RoutingContext ctx) {
        this.params = ctx.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    }

    public int getAdriaId() {
        return params.pathParameter("adriaId").getInteger();
    }

    public int getEntryId() {
        return params.pathParameter("entry").getInteger();
    }

    public String stringFromParam(String paramName)
    {
        if(params.queryParameter(paramName) != null && params.queryParameter(paramName).isString())
        {
            return params.queryParameter(paramName).getString();
        }
        LOGGER.log(Level.WARNING, PARAM_NOT_FOUND_MSG);
        return "";
    }

    public String stringFromPath(String paramName)
    {
        if(params.pathParameter(paramName) != null && params.pathParameter(paramName).isString())
        {
            return params.pathParameter(paramName).getString();
        }
        LOGGER.log(Level.WARNING, PARAM_NOT_FOUND_MSG);
        return "";
    }

    public int intFromParam(String paramName)
    {
        if(params.queryParameter(paramName) != null && params.queryParameter(paramName).isNumber())
        {
            return params.queryParameter(paramName).getInteger();
        }
        LOGGER.log(Level.WARNING, PARAM_NOT_FOUND_MSG);
        return -1;
    }

    /**
     * gets the boolean from the requests path params 
     * 
     * @param paramName
     * @return 1 = boolean was set to true
     * <li>0 = boolean was set to false</li>
     * <li>-1 = boolean not set in path params</li>
     */
    public int getBoolPathParam(String paramName) {
        if(params.queryParameter(paramName) != null && params.queryParameter(paramName).isBoolean())
        {
            return Boolean.TRUE.equals(params.queryParameter(paramName).getBoolean()) ? 1 : 0;
        }
        return -1;
    }

    public String getStringPathParam(String paramName) {
        if(params.queryParameter(paramName) != null && params.queryParameter(paramName).isString())
        {
            return params.queryParameter(paramName).getString();
        }
        return "";
    }

    public int intFromPath(String paramName)
    {
        if(params.pathParameter(paramName) != null && params.pathParameter(paramName).isNumber())
        {
            return params.pathParameter(paramName).getInteger();
        }
        LOGGER.log(Level.WARNING, PARAM_NOT_FOUND_MSG);
        return -1;
    }

    public String getPartOfDataFromBody(String key) {
        try {
            if (params.body().isJsonObject())
                return params.body().getJsonObject().getJsonObject("data").getString(key);
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher the data in the body", ex);
            throw new MalformedRequestException("Unable to decipher the data in the request body.");
        }
    }

    public JsonObject getBody() {
        if (params.body().isJsonObject()) {
            return params.body().getJsonObject();
        } else {
            throw new IllegalStateException("body was not json");
        }
    }
}
