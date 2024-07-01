package com.example.projetogestaofaltas.core.domain.exceptions;

public class MateriaNaoEncontrada  extends EntidadeNaoEncontrada{

        private static final long serialVersionUID = 1L;
        public MateriaNaoEncontrada(String mensagem) {
            super(mensagem);
        }

        public MateriaNaoEncontrada(Long materiaId) {
            this(String.format("Não existe um cadastro de materia com código %d", materiaId));
        }
}
