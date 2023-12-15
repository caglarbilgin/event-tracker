package com.event.tracker.eventtracker.exception;

public class CustomException extends RuntimeException {
    public enum ErrorCode {
        USER_ALREADY_EXIST("User already exists !");

        private final String value;

        ErrorCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
