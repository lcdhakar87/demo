package com.example.demo.builder;

import com.example.demo.model.UserEntiy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public final class SuccessResponseBuilder {

    private static final Locale DEFAULT_LOCALE = Locale.US;

    public ResponseEntity<?> build(Object responseEntity, UserEntiy activeUser){
        return ResponseEntity.ok(responseEntity);
    }

    public ResponseEntity<?> build(Object responseEntity){
        return ResponseEntity.ok(responseEntity);
    }

}
