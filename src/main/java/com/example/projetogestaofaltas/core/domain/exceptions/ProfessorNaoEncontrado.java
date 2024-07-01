package com.example.projetogestaofaltas.core.domain.exceptions;

public class ProfessorNaoEncontrado extends EntidadeNaoEncontrada{

        private static final long serialVersionUID = 1L;
        public ProfessorNaoEncontrado(String mensagem) {
            super(mensagem);
        }

        public ProfessorNaoEncontrado(Long materiaId) {
            this(String.format("Não existe um cadastro de professor com código %d", materiaId));
        }
}
