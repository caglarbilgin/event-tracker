package com.event.tracker.eventtracker.exception;

public class CustomException extends RuntimeException {
    public enum ErrorCode {
        USER_ALREADY_EXIST("User already exists !"),
        USER_NOT_FOUND("User not found !"),
        MAX_ADDRESS_SIZE("You have exceeded the maximum number of addresses, please delete an address and try again."),
        ADDRESS_NOT_FOUND("Address not found"),
        EVENT_NOT_FOUND("Event not found");

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
