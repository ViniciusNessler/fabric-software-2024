package com.example.projetogestaofaltas.core.domain.exceptions;

public class AlunoNaoEncontrado extends EntidadeNaoEncontrada{
    private static final long serialVersionUID = 1L;

    public AlunoNaoEncontrado(String mensagem)
    {
        super(mensagem);

    }

    public AlunoNaoEncontrado(Long estadoId)
    {
        this(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
