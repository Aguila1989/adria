package be.howest.ti.adria.web;

/**
 * a little utility class so we don't have http codes as magic numbers
 */
public class HttpResponseCode {
    public static final int NO_CONTENT = 204;
    public static final int CREATED = 201;
    public static final int OK = 200;
    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;
    private HttpResponseCode(){}
}
