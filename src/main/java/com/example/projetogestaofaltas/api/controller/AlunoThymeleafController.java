package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.service.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/alunos")
public class AlunoThymeleafController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/gerenciar")
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.listar());
        return "alunos";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "adicionarAluno";
    }

    @PostMapping("/adicionar")
    public String adicionarAluno(@ModelAttribute Aluno aluno) {
        aluno.getId();
        alunoService.salvar(aluno);
        return "redirect:/alunos/gerenciar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", alunoService.procurar(id));
        return "editarAluno";
    }

    @PostMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, @ModelAttribute Aluno aluno) {
        Aluno alunoExistente = alunoService.procurar(id);
        BeanUtils.copyProperties(aluno, alunoExistente, "id");
        alunoService.salvar(alunoExistente);
        return "redirect:/alunos/gerenciar";
    }

    @GetMapping("/remover/{id}")
    public String removerAluno(@PathVariable Long id) {
        alunoService.remover(id);
        return "redirect:/alunos/gerenciar";
    }
}
