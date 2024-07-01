package com.example.projetogestaofaltas.core.service;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlunoService {
    List<Aluno> listar();
    Aluno salvar(Aluno aluno);

    Aluno procurar(Long id);
    void remover(Long id);
}
