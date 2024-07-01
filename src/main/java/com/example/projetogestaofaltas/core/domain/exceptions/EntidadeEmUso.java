package com.example.projetogestaofaltas.core.domain.exceptions;

public class EntidadeEmUso extends RuntimeException{
    public EntidadeEmUso(String mensagem) {
        super(mensagem);
    }
}
