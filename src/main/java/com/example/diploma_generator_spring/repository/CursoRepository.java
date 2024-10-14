package com.example.diploma_generator_spring.repository;

import com.example.diploma_generator_spring.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
