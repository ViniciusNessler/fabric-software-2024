package com.example.projetogestaofaltas.core.repository;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia,Long> {
    @Query("SELECT m FROM Materia m JOIN m.lista_alunos a WHERE a = :aluno")
    List<Materia> findMateriasByAluno(Aluno aluno);
}
