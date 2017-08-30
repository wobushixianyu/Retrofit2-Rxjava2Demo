package com.jery.test.retrofit2rxjava2demo.httpframe.model;


import com.google.gson.annotations.SerializedName;

public class Response {

    public Response() {
    }

    @SerializedName("ResultCode")
    private int code;
    @SerializedName("Message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return code == 1000;
    }

    public boolean isCodeInvalid() {
        return code != 1000 && code != 7001;
    }
}
