package com.example.projetogestaofaltas.core.service.impl;

import com.example.projetogestaofaltas.core.domain.exceptions.AlunoNaoEncontrado;
import com.example.projetogestaofaltas.core.domain.exceptions.EntidadeEmUso;
import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.repository.AlunoRepository;
import com.example.projetogestaofaltas.core.service.AlunoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @Override
    @Transactional
    public Aluno salvar(Aluno aluno) {

        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno procurar(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontrado(id));
    }

    @Override
    @Transactional
    public void remover(Long id) {

        try {
            alunoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AlunoNaoEncontrado(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUso(
                    String.format("Aluno com id %d está sendo utilizado por outra entidade," +
                            " por isso não pode ser removido", id));
        }

    }
}
