package com.example.demo.repository;

import com.example.demo.model.UserEntiy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntiy,Long> {

    boolean existsByEmail(String email);

    Optional<UserEntiy> findByEmail(String email);
}
