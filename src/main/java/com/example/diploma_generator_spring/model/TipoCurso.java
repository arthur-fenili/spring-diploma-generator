package com.example.diploma_generator_spring.model;

public enum TipoCurso {
    POS_GRADUACAO("Pós-Graduação"),
    GRADUACAO("Graduação");

    private String tipoCurso;

    TipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
}
