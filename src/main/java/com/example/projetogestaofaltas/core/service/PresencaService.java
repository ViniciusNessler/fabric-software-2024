package com.example.projetogestaofaltas.core.service;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.domain.model.Presenca;

import java.time.LocalDate;
import java.util.List;

public interface PresencaService {
    void registrarPresenca(Aluno aluno, Materia materia, LocalDate data, boolean presente);
    List<Presenca> listarPresencas(Aluno aluno, Materia materia);
    double calcularPresenca(Aluno aluno, Materia materia);
    int calcularFaltas(Aluno aluno, Materia materia);
    void registrarFalta(Aluno aluno, Materia materia, LocalDate data);

}
