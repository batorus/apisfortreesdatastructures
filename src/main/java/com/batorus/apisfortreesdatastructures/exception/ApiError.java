package com.batorus.apisfortreesdatastructures.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@ResponseBody
public class ApiError {

    int status;

    String message;

    //long timestamp;
    String time;
    String path;

    Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        super();
        long timestamp = new Date().getTime();
        //Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        time = dateFormat.format(timestamp);
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

//    public void setTimestamp(long timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public long getTimestamp() {
//        return timestamp;
//    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

}