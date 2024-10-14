package com.example.diploma_generator_spring.repository;

import com.example.diploma_generator_spring.model.Diplomado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiplomadoRepository extends JpaRepository<Diplomado, Long> {
}
