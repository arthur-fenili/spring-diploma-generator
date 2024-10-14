package com.example.diploma_generator_spring.repository;

import com.example.diploma_generator_spring.model.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiplomaRepository extends JpaRepository<Diploma, Long> {
}
