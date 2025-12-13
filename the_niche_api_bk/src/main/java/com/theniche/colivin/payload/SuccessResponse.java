package com.theniche.colivin.payload;

public class SuccessResponse implements BaseResponse{
    private final String action;
    private final String resourceId;

    public SuccessResponse(String action, String resourceId) {
        this.action = action;
        this.resourceId = resourceId;
    }

    public String getAction() {
        return action;
    }

    public String getResourceId() {
        return resourceId;
    }
}
