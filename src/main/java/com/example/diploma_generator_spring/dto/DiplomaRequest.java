package com.example.diploma_generator_spring.dto;

import com.example.diploma_generator_spring.model.Sexo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record DiplomaRequest(
        @NotNull(message = "O id do diplomado é obrigatório.")
        Long diplomadoId,
        @NotNull(message = "O id do curso é obrigatório.")
        Long cursoId,
        @NotNull(message = "A data de conclusão é obrigatória.")
        @PastOrPresent(message = "A data de conclusão deve ser no passado ou presente.")
        Date dataConclusao,
        @NotNull(message = "O sexo do reitor é obrigatório.")
        Sexo sexoReitor,
        @NotNull(message = "O nome do reitor é obrigatório.")
        String nomeReitor
) {
}
