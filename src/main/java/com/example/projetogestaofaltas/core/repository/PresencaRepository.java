package com.example.projetogestaofaltas.core.repository;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.domain.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    List<Presenca> findByAlunoAndMateria(Aluno aluno, Materia materia);
}

