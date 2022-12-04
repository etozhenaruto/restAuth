package com.example.restAuth.pojo;

public class MessageResponse {

    private String message;
    private String code;
    private String time;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
