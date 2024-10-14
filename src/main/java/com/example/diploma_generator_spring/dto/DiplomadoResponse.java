package com.example.diploma_generator_spring.dto;

public record DiplomadoResponse(
        Long id,
        String nome,
        String nacionalidade,
        String naturalidade,
        String rg
) {
}
