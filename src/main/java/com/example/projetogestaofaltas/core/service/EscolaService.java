package com.example.projetogestaofaltas.core.service;

import com.example.projetogestaofaltas.core.domain.model.Escola;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface EscolaService {
    List<Escola> listar();
    Escola salvar(Escola escola);
    Escola procurar(Long id);
    void remover(Long id);
}
