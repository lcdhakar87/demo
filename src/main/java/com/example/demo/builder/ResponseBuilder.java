package com.example.demo.builder;

import com.example.demo.model.UserEntiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    @Autowired
    private ErrorResponseBuilder errorResponseBuilder;

    @Autowired
    private SuccessResponseBuilder successResponseBuilder;


    public ResponseEntity<?> error(HttpStatus httpStatus, String message){
        return errorResponseBuilder.build(httpStatus, message);
    }

    public ResponseEntity<?> error(HttpStatus httpStatus, Exception e){
        return errorResponseBuilder.build(httpStatus, e);
    }

    public ResponseEntity<?> ok(Object responseObject){
        return successResponseBuilder.build(responseObject);
    }

    public ResponseEntity<?> ok(Object responseObject, UserEntiy userDetails){
        return successResponseBuilder.build(responseObject, userDetails);
    }

}
