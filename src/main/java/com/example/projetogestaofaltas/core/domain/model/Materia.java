package com.example.projetogestaofaltas.core.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    @ManyToMany
    @JoinTable(name = "materia_aluno",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> lista_alunos;

    public Materia() {
    }

    public Materia(Long id, String nome, String descricao, Professor professor, List<Aluno> lista_alunos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
        this.lista_alunos = lista_alunos;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", professor=" + (professor != null ? professor.getNome() : "null") + // Evita recursão aqui
                '}';
    }
}
