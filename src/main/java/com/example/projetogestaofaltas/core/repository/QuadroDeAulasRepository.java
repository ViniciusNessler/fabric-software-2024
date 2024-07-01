package com.example.projetogestaofaltas.core.repository;

import com.example.projetogestaofaltas.core.domain.model.QuadroDeAulas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadroDeAulasRepository extends JpaRepository<QuadroDeAulas,Long> {
}
