package com.prospring.ch16.util;

public record Message(String type, String message) {

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
