package com.attendance.Common;

import java.util.Date;

public class SuccessDetails {
    private Date timestamp;
    private String message;
    private Object data;

    public SuccessDetails(){}

    public SuccessDetails(Date timestamp, String message, Object data) {
        this.timestamp = timestamp;
        this.message = message;
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
