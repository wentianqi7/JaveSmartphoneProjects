package com.example.skyforlife.projectwithactivity.exception;

import com.example.skyforlife.projectwithactivity.utils.Logger;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class CustomException extends Exception {
    public enum ExceptionType {
        INVALID_INPUT,
        GPS_NOT_AVAILABLE,
        REQUEST_FAILED,
        SPACE_IN_REQUEST
    }
    private String message;
    private ExceptionType type;

    public CustomException(String message, ExceptionType type) {
        this.message = message;
        this.type = type;
    }

    @Override
    public String toString() {
        return message;
    }

    public void fix() {
        switch (type) {
            case INVALID_INPUT:
                Logger.log("Invalid input", 1);
                break;
            case GPS_NOT_AVAILABLE:
                Logger.log("GPS not available", 1);
                break;
            case REQUEST_FAILED:
                Logger.log("Request failed", 1);
                break;
        }
    }

    public String fix(String input) {
        return input.replaceAll(" ", "");
    }
}
