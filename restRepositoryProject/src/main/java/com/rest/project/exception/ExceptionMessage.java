package com.rest.project.exception;

import java.util.Date;

public class ExceptionMessage {

    private Date timestamp;
    private String message;
    private String path;
    private String details;

    public ExceptionMessage(Date timestamp, String message, String path, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
