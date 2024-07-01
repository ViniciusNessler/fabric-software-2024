package com.example.projetogestaofaltas.core.service.impl;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.domain.model.Presenca;
import com.example.projetogestaofaltas.core.repository.PresencaRepository;
import com.example.projetogestaofaltas.core.service.PresencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PresencaServiceImpl implements PresencaService {
    @Autowired
    private PresencaRepository presencaRepository;
    @Override
    public void registrarPresenca(Aluno aluno, Materia materia, LocalDate data, boolean presente) {
        Presenca presenca = new Presenca();
        presenca.setAluno(aluno);
        presenca.setMateria(materia);
        presenca.setData(data);
        presenca.setPresente(presente);
        presencaRepository.save(presenca);
    }

    @Override
    public List<Presenca> listarPresencas(Aluno aluno, Materia materia) {
        return presencaRepository.findByAlunoAndMateria(aluno, materia);
    }

    @Override
    public double calcularPresenca(Aluno aluno, Materia materia) {
        List<Presenca> presencas = presencaRepository.findByAlunoAndMateria(aluno, materia);
        long totalPresencas = presencas.stream().filter(Presenca::isPresente).count();
        return (totalPresencas / 36.0) * 100;
    }

    @Override
    public int calcularFaltas(Aluno aluno, Materia materia) {
        List<Presenca> presencas = presencaRepository.findByAlunoAndMateria(aluno, materia);
        return (int) presencas.stream().filter(p -> !p.isPresente()).count();
    }

    @Override
    public void registrarFalta(Aluno aluno, Materia materia, LocalDate data) {
        Presenca falta = new Presenca(aluno, materia, data, false);
        presencaRepository.save(falta);
    }
}
