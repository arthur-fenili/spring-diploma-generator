package com.example.diploma_generator_spring.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diplomado_id", nullable = false)
    private Diplomado diplomado;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "data_de_conclusao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDeConclusao;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo_reitor")
    private Sexo sexoReitor;

    @Column(name = "nome_reitor")
    private String nomeReitor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diplomado getDiplomado() {
        return diplomado;
    }

    public void setDiplomado(Diplomado diplomado) {
        this.diplomado = diplomado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getDataDeConclusao() {
        return dataDeConclusao;
    }

    public void setDataDeConclusao(Date dataDeConclusao) {
        this.dataDeConclusao = dataDeConclusao;
    }

    public Sexo getSexoReitor() {
        return sexoReitor;
    }

    public void setSexoReitor(Sexo sexoReitor) {
        this.sexoReitor = sexoReitor;
    }

    public String getNomeReitor() {
        return nomeReitor;
    }

    public void setNomeReitor(String nomeReitor) {
        this.nomeReitor = nomeReitor;
    }
}
