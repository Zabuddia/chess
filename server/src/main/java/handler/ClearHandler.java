package handler;

import com.google.gson.Gson;
import request.ClearRequest;
import response.ClearResponse;
import service.ClearService;
import spark.Request;
import spark.Response;

public class ClearHandler {
    private final Gson gson = new Gson();
    private final ClearService clearService = new ClearService();
    public String handleClear(Request request, Response response) {
        ClearRequest clearRequest = gson.fromJson(request.body(), ClearRequest.class);

        ClearResponse clearResponse = clearService.clear(clearRequest);
        response.status(200);

        return gson.toJson(clearResponse);
    }
}
