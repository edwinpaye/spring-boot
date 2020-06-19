package com.rest.project.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceptionMessage {

    private String message;
    private Date timestamp;
    private String path;
    private List<String> details;

    public ExceptionMessage(String message, Date timestamp, String path, List<String> details) {
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
        this.details = details;
    }

    public ExceptionMessage(String message, Date timestamp, String path, String details) {
        this(message, timestamp, path, new ArrayList<String>());
        this.details.add(details);
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
