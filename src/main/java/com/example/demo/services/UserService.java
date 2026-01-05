package com.example.demo.services;

import com.example.demo.model.UserEntiy;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntiy registration(UserEntiy user) throws Exception {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already exists!");
        }

        // Encode the password before saving
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public List<UserEntiy> userList(){
        return userRepository.findAll();
    }
}
