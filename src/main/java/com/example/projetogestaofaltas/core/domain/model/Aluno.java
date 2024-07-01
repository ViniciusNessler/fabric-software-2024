package com.example.projetogestaofaltas.core.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Data
@Builder
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_nascimento;
    @Column(nullable = false)
    private String email;
    private String telefone;

    public Aluno() {
    }

    public Aluno(Long id, String nome, Date data_nascimento, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.telefone = telefone;
    }
}
