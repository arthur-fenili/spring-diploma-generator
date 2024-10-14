package com.example.diploma_generator_spring.repository;

import com.example.diploma_generator_spring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByLogin(String login);
}
