package com.example.projetogestaofaltas.core.domain.exceptions;

public class EscolaNaoEncontrada extends EntidadeNaoEncontrada{

    private static final long serialVersionUID = 1L;
    public EscolaNaoEncontrada(String mensagem) {
        super(mensagem);
    }

    public EscolaNaoEncontrada(Long escolaId) {
        this(String.format("Não existe um cadastro de escola com código %d", escolaId));
    }
}
