package com.example.projetogestaofaltas.core.repository;

import com.example.projetogestaofaltas.core.domain.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRepository extends JpaRepository<Escola,Long> {
}
