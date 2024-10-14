package com.example.diploma_generator_spring.service;

import com.example.diploma_generator_spring.dto.CursoRequest;
import com.example.diploma_generator_spring.dto.CursoResponse;
import com.example.diploma_generator_spring.model.Curso;
import org.springframework.stereotype.Service;

@Service
public class CursoMapper {

    public Curso requestToCurso(CursoRequest request) {
        Curso curso = new Curso();
        curso.setNome(request.nome());
        curso.setTipo(request.tipo());
        return curso;
    }

    public CursoResponse cursoToResponse(Curso curso) {
        return new CursoResponse(curso.getId(), curso.getNome(), curso.getTipo());
    }
}
