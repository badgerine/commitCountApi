package com.vortimo.sbu.commitCounter.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<String> subMessages;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(String message, List<String> subMessages) {
        this();
        this.message = message;
        this.subMessages = subMessages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSubMessages() {
        return subMessages;
    }

    public void setSubMessages(List<String> subMessages) {
        this.subMessages = subMessages;
    }
}
