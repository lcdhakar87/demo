package com.example.demo.builder;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

@Component
public class ErrorResponseBuilder {

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<?> build(HttpStatus httpStatus, String message){
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(message, httpStatus.value(), httpStatus.getReasonPhrase(), new Date());
        return ResponseEntity.status(httpStatus).body(errorResponseEntity);
    }

    public ResponseEntity<?> build(HttpStatus httpStatus, Exception e){
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity(e.getMessage(), httpStatus.value(), httpStatus.getReasonPhrase(), new Date());
        if(StringUtils.equalsIgnoreCase(request.getParameter("stacktrace"), "true")){
            errorResponseEntity.setStackTrace(ExceptionUtils.getStackTrace(e));
        }
        return ResponseEntity.status(httpStatus).body(errorResponseEntity);
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
final class ErrorResponseEntity{
    private String message;
    private Integer status;
    private String error;
    private Date timestamp;
    private String stackTrace;

    public ErrorResponseEntity(String message, Integer status, String error, Date timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
