package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.DTO.AlunoFrequenciaDTO;
import com.example.projetogestaofaltas.core.domain.model.DTO.MateriaDTO;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.service.AlunoService;
import com.example.projetogestaofaltas.core.service.MateriaService;
import com.example.projetogestaofaltas.core.service.PresencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/presencas")
public class PresencaThymeleafController {
    @Autowired
    private PresencaService presencaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MateriaService materiaService;

    @PostMapping("/registrar")
    public String registrarPresenca(@RequestParam Long alunoId,
                                    @RequestParam(required = false) Long materiaId,
                                    @RequestParam(required = false) String data,
                                    @RequestParam(required = false) Boolean presente) {
        Aluno aluno = alunoService.procurar(alunoId);
        Materia materia = materiaService.procurar(materiaId);
        LocalDate dataPresenca = LocalDate.parse(data);
        if (presente == null || !presente) {
            presente = false;
            presencaService.registrarFalta(aluno, materia, dataPresenca);
        } else {
            presencaService.registrarPresenca(aluno, materia, dataPresenca, true);
        }
        return "redirect:/presencas/registrar";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistrar(Model model, @RequestParam(required = false) Long alunoId) {
        List<Aluno> alunos = alunoService.listar();
        model.addAttribute("alunos", alunos);

        if (alunoId != null) {
            Aluno aluno = alunoService.procurar(alunoId);
            List<Materia> materias = materiaService.listarMateriasPorAluno(aluno);
            model.addAttribute("materias", materias);
            model.addAttribute("selectedAluno", aluno);
        } else {
            model.addAttribute("materias", new ArrayList<Materia>());
        }

        return "registrarPresenca";
    }

    @GetMapping("/materiasPorAluno")
    @ResponseBody
    public List<MateriaDTO> listarMateriasPorAluno(@RequestParam Long alunoId) {
        Aluno aluno = alunoService.procurar(alunoId);
        List<Materia> materias = materiaService.listarMateriasPorAluno(aluno);

        // Transforma a lista de matérias em uma lista de DTOs simplificados
        List<MateriaDTO> materiaDTOs = new ArrayList<>();
        for (Materia materia : materias) {
            materiaDTOs.add(new MateriaDTO(materia.getId(), materia.getNome()));
        }

        return materiaDTOs;
    }


    @GetMapping("/frequencia")
    public String calcularFrequenciaPorMateria(Model model) {
        List<Materia> materias = materiaService.listar();
        Map<Materia, List<AlunoFrequenciaDTO>> frequenciaPorMateria = new HashMap<>();

        for (Materia materia : materias) {
            List<AlunoFrequenciaDTO> alunoFrequenciaList = new ArrayList<>();
            for (Aluno aluno : alunoService.listar()) {
                double frequencia = presencaService.calcularPresenca(aluno, materia);
                int faltas = presencaService.calcularFaltas(aluno, materia);
                alunoFrequenciaList.add(new AlunoFrequenciaDTO(aluno, frequencia, faltas));
            }
            frequenciaPorMateria.put(materia, alunoFrequenciaList);
        }

        model.addAttribute("frequenciaPorMateria", frequenciaPorMateria);
        return "frequenciaAluno";
    }

}
