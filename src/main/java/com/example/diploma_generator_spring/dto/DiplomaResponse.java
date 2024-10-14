package com.example.diploma_generator_spring.dto;

import com.example.diploma_generator_spring.model.Diplomado;
import com.example.diploma_generator_spring.model.Sexo;

import java.util.Date;

public record DiplomaResponse(
        Long id,
        Diplomado diplomado,
        String curso,
        Date dataConclusao,
        Sexo sexoReitor,
        String nomeReitor
) {
}
