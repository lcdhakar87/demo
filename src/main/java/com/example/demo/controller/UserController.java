package com.example.demo.controller;

import com.example.demo.builder.ResponseBuilder;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.UserEntiy;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private final ResponseBuilder responseBuilder = null;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserEntiy user) {
            
        try{
            return ResponseEntity.ok(userService.registration(user));
        }catch (DataIntegrityViolationException dex){
            return responseBuilder.error(HttpStatus.UNPROCESSABLE_ENTITY, "Email is already exists");
        } catch (Exception ex){
            return responseBuilder.error(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        UserEntiy user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null ||
                !passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
        String token =  jwtUtil.generateToken(user.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/profile")
    public ResponseEntity profile() {

        Map<String, String> response = new HashMap<>();
        response.put("Status", "Success");

        return ResponseEntity.ok(response);

    }
}
