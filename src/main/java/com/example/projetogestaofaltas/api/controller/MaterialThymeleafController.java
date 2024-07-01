package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.domain.model.Professor;
import com.example.projetogestaofaltas.core.service.AlunoService;
import com.example.projetogestaofaltas.core.service.MateriaService;
import com.example.projetogestaofaltas.core.service.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/materias")
public class MaterialThymeleafController {
    @Autowired
    private MateriaService materiaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    // Listar todas as matérias
    @GetMapping("/gerenciar")
    public String listarMaterias(Model model) {
        List<Materia> materias = materiaService.listar();
        model.addAttribute("materias", materias);
        return "materias";
    }

    // Formulário para adicionar uma nova matéria
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        List<Professor> professores = professorService.listar();
        List<Aluno> alunos = alunoService.listar();
        model.addAttribute("materia", new Materia());
        model.addAttribute("professores", professores);
        model.addAttribute("alunos", alunos);
        return "adicionarMaterias";
    }

    // Adicionar uma nova matéria
    @PostMapping("/adicionar")
    public String adicionarMateria(@ModelAttribute Materia materia,
                                   @RequestParam("professorId") Long professorId) {
        materiaService.salvarSoProfessor(materia, professorId);
        return "redirect:/materias/gerenciar";
    }

    // Exibir detalhes de uma matéria

    // Exibir detalhes de uma matéria
    @GetMapping("/detalhes/{id}")
    public String mostrarDetalhesMateria(@PathVariable Long id, Model model) {
        Materia materia = materiaService.procurar(id);

        if (materia != null) {
            List<Aluno> alunosDisponiveis = alunoService.listar().stream()
                    .filter(aluno -> !materia.getLista_alunos().contains(aluno))
                    .collect(Collectors.toList());
            model.addAttribute("materia", materia);
            model.addAttribute("alunosDisponiveis", alunosDisponiveis);
            return "detalhes";
        } else {
            return "redirect:/materias/gerenciar";
        }
    }

    // Adicionar um aluno à matéria
    @PostMapping("/detalhes/adicionar-aluno")
    public String adicionarAlunoMateria(@RequestParam Long id, @RequestParam Long alunoId) {
        /*Materia materiaAtual = materiaService.procurar(id);
        Materia materiaNova = new Materia();
        BeanUtils.copyProperties(materiaAtual, materiaNova, "id");
        Aluno aluno = alunoService.procurar(alunoId);
        List<Aluno> alunos = materiaNova.getLista_alunos();
        alunos.add(aluno);
        materiaNova.setLista_alunos(alunos);
        materiaService.salvar(materiaNova);
        return "redirect:/materias/detalhes/"+String.valueOf(id)+"";*/
        Materia materiaExistente = materiaService.procurar(id);
        if (materiaExistente != null) {
            Aluno aluno = alunoService.procurar(alunoId);
            if (aluno != null) {
                List<Aluno> alunos = materiaExistente.getLista_alunos();
                if (!alunos.contains(aluno)) {
                    alunos.add(aluno);
                    materiaExistente.setLista_alunos(alunos);
                    materiaService.salvar(materiaExistente);
                }
            }
        }
        return "redirect:/materias/detalhes/" + id;
    }

    // Remover um aluno da matéria
    @PostMapping("/detalhes/remover-aluno")
    public String removerAlunoMateria(@RequestParam Long id, @RequestParam Long alunoId) {
        Materia materiaExistente = materiaService.procurar(id);
        if (materiaExistente != null) {
            Aluno aluno = alunoService.procurar(alunoId);
            if (aluno != null) {
                List<Aluno> alunos = materiaExistente.getLista_alunos();
                if (alunos.contains(aluno)) {
                    alunos.remove(aluno);
                    materiaExistente.setLista_alunos(alunos);
                    materiaService.salvar(materiaExistente);
                }
            }
        }
        return "redirect:/materias/detalhes/" + id;
    }
}
