package com.example.projetogestaofaltas.core.repository;

import com.example.projetogestaofaltas.core.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
