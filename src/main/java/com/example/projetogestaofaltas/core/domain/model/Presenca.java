package com.example.projetogestaofaltas.core.domain.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Data
@Entity
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    private LocalDate data;
    private boolean presente;

    public Presenca() {
    }

    public Presenca(Aluno aluno, Materia materia, LocalDate data, boolean b) {
        this.aluno = aluno;
        this.materia = materia;
        this.data = data;
        this.presente = b;
    }

    // Getters e Setters
}

