package com.example.diploma_generator_spring.dto;

import com.example.diploma_generator_spring.model.TipoCurso;

public record CursoResponse(
        Long id,
        String nome,
        TipoCurso tipo
) {
}
