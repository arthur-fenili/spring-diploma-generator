package com.example.diploma_generator_spring.model;

import jakarta.persistence.*;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCurso tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoCurso tipo) {
        this.tipo = tipo;
    }
}
