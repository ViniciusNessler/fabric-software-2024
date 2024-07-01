package com.example.projetogestaofaltas.core.service.impl;

import com.example.projetogestaofaltas.core.domain.exceptions.EntidadeEmUso;
import com.example.projetogestaofaltas.core.domain.exceptions.MateriaNaoEncontrada;
import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.domain.model.Professor;
import com.example.projetogestaofaltas.core.repository.MateriaRepository;
import com.example.projetogestaofaltas.core.service.MateriaService;
import com.example.projetogestaofaltas.core.service.ProfessorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfessorService professorService;

    @Override
    public List<Materia> listar() {
        return materiaRepository.findAll();
    }

    @Override
    @Transactional
    public Materia salvar(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public List<Materia> listarMateriasPorAluno(Aluno aluno) {
        return materiaRepository.findMateriasByAluno(aluno);
    }

    @Transactional
    @Override
    public Materia salvarSoProfessor(Materia materia, Long professorId) {
        Professor professor = professorService.procurar(professorId);
        materia.setProfessor(professor);
        return materiaRepository.save(materia);
    }

    @Override
    public Materia procurar(Long id) {
        return materiaRepository.findById(id)
            .orElseThrow(() -> new MateriaNaoEncontrada(id));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        try{
            materiaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new MateriaNaoEncontrada(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUso(
                String.format("Materia com id %d está sendo utilizada por outra entidade," +
                    " por isso não pode ser removida", id));
        }
    }
}
