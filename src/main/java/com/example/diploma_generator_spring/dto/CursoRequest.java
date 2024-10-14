package com.example.diploma_generator_spring.dto;

import com.example.diploma_generator_spring.model.TipoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequest(

        @NotBlank(message = "O nome do curso é obrigatório")
        String nome,
        @NotNull(message = "O nome do curso é obrigatório")
        TipoCurso tipo
) {
}
