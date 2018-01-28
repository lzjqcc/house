package com.qcc.exception;

public class BuisnessException extends RuntimeException {
    private int code;
    private String message;
    private Exception e;

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
    public BuisnessException(int code, String message, Exception e) {
        this.code = code;
        this.message = message;
        this.e = e;
    }
    public BuisnessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
