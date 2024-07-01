package com.example.projetogestaofaltas.core.domain.model.DTO;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlunoFrequenciaDTO {

    private Aluno aluno;
    private double frequencia;
    private int faltas;

    public AlunoFrequenciaDTO(Aluno aluno, double frequencia, int faltas) {
        this.aluno = aluno;
        this.frequencia = frequencia;
        this.faltas = faltas;
    }
}
