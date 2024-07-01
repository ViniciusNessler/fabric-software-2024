package com.example.projetogestaofaltas.core.service;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;

import java.util.List;


public interface MateriaService {
    List<Materia> listar();
    Materia salvar(Materia materia);
    Materia salvarSoProfessor(Materia materia,Long professorId);
    Materia procurar(Long id);
    List<Materia> listarMateriasPorAluno(Aluno aluno);
    void remover(Long id);
}
