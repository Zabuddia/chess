package handler;

import com.google.gson.Gson;
import request.ClearRequest;
import response.ClearResponse;
import service.ClearService;

public class ClearHandler {
    private final Gson gson = new Gson();
    private final ClearService clearService = new ClearService();
    public String handleClear(String jsonClearRequest) {
        ClearRequest clearRequest = gson.fromJson(jsonClearRequest, ClearRequest.class);

        ClearResponse clearResponse = clearService.clear(clearRequest);
        return gson.toJson(clearResponse);
    }
}
