package com.jery.test.retrofit2rxjava2demo.httpframe;

public class ApiException extends RuntimeException {

    private int errorCode;

    public ApiException(int code, String errorMessage) {
        super(errorMessage);
        errorCode = code;
    }

    public int getCode() {
        return errorCode;
    }

    boolean isTokenInvalid() {
        return errorCode == 1009;
    }
}
