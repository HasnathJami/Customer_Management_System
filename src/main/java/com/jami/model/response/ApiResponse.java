package com.jami.model.response;


public class ApiResponse<T> {

    private boolean is_success;
    private String message;
    private String status;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean is_success, String message) {
        this.is_success = is_success;
        this.message = message;
    }

    public ApiResponse(boolean is_success, String message, T data) {
        this.is_success = is_success;
        this.message = message;
        this.data = data;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
