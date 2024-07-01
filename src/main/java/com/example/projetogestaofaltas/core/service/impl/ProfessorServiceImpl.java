package com.example.projetogestaofaltas.core.service.impl;

import com.example.projetogestaofaltas.core.domain.exceptions.EntidadeEmUso;
import com.example.projetogestaofaltas.core.domain.exceptions.MateriaNaoEncontrada;
import com.example.projetogestaofaltas.core.domain.exceptions.ProfessorNaoEncontrado;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.domain.model.Professor;
import com.example.projetogestaofaltas.core.repository.ProfessorRepository;
import com.example.projetogestaofaltas.core.service.MateriaService;
import com.example.projetogestaofaltas.core.service.ProfessorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    @Override
    @Transactional
    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor procurar(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNaoEncontrado(id));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        try {
            professorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new MateriaNaoEncontrada(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUso(
                    String.format("Professor com id %d está sendo utilizada por outra entidade," +
                            " por isso não pode ser removida", id));
        }
    }
}
