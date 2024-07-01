package com.example.projetogestaofaltas.core.domain.model.DTO;

import lombok.Data;

@Data
public class MateriaDTO {
    private Long id;
    private String nome;

    public MateriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
