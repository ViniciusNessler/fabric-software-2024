package com.example.projetogestaofaltas.core.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Builder
public class Professor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    private String telefone;
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Materia> materias;

    public Professor() {

    }

    public Professor(Long id, String nome, String email, String telefone, List<Materia> materias) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.materias = materias;
    }
}
