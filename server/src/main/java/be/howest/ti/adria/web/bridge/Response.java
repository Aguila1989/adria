package be.howest.ti.adria.web.bridge;

import java.util.List;

import be.howest.ti.adria.web.HttpResponseCode;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
* The Response class is responsible for translating the result of the controller into
* JSON responses with an appropriate HTTP code.
*/
public class Response {
    private Response() {}

    public static void sendSuccessfullUpdate(RoutingContext ctx) {
        sendEmptyResponse(ctx, HttpResponseCode.NO_CONTENT);
    }

    public static void sendCreatedResponse(RoutingContext ctx) {
        sendEmptyResponse(ctx, HttpResponseCode.CREATED);
    }

    private static void sendEmptyResponse(RoutingContext ctx, int statusCode) {
        ctx.response()
        .setStatusCode(statusCode)
        .end();
    }

    private static void sendJsonResponse(RoutingContext ctx, int statusCode, Object response) {
        ctx.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        .setStatusCode(statusCode)
        .end(Json.encodePrettily(response));
    }

    public static void sendOkJsonResponse(RoutingContext ctx, JsonObject response) {
        sendJsonResponse(ctx, HttpResponseCode.OK, response);
    }

    public static void sendFailure(RoutingContext ctx, int code, String message) {
        sendJsonResponse(ctx, code, new JsonObject()
        .put("failure", code)
        .put("cause", message));
    }

    public static void sendMultiFailure(RoutingContext ctx, int code, List<String> errors) {
        sendJsonResponse(ctx, code, new JsonObject()
        .put("failure", code)
        .put("cause", errors));
    }
}
