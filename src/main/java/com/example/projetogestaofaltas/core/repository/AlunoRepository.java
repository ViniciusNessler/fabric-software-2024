package com.example.projetogestaofaltas.core.repository;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {

}
