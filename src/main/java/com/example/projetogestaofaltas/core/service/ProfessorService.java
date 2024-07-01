package com.example.projetogestaofaltas.core.service;

import com.example.projetogestaofaltas.core.domain.model.Professor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfessorService {
    List<Professor> listar();
    Professor salvar(Professor professor);
    Professor procurar(Long id);
    void remover(Long id);

}
