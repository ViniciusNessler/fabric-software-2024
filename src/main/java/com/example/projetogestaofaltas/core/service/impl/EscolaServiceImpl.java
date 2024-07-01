package com.example.projetogestaofaltas.core.service.impl;

import com.example.projetogestaofaltas.core.domain.exceptions.EntidadeEmUso;
import com.example.projetogestaofaltas.core.domain.exceptions.EscolaNaoEncontrada;
import com.example.projetogestaofaltas.core.domain.model.Escola;
import com.example.projetogestaofaltas.core.repository.EscolaRepository;
import com.example.projetogestaofaltas.core.service.EscolaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EscolaServiceImpl implements EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

    @Override
    public List<Escola> listar() {
        return escolaRepository.findAll();
    }

    @Override
    @Transactional
    public Escola salvar(Escola escola) {
        return escolaRepository.save(escola);
    }

    @Override
    public Escola procurar(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new EscolaNaoEncontrada(id));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        try {
            escolaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EscolaNaoEncontrada(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUso(
                    String.format("Escola com id %d está sendo utilizada por outra entidade," +
                            " por isso não pode ser removida", id));
        }
    }
}
