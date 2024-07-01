package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Professor;
import com.example.projetogestaofaltas.core.service.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professores")
public class ProfessorThymeleafController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/listar")
    public String listarProfessores(Model model) {
        model.addAttribute("professores", professorService.listar());
        return "professores";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("professor", new Professor());
        return "adicionarProfessor";
    }

    @PostMapping("/adicionar")
    public String adicionarProfessor(@ModelAttribute Professor professor) {
        professorService.salvar(professor);
        return "redirect:/professores/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("professor", professorService.procurar(id));
        return "editarProfessor";
    }

    @PostMapping("/editar/{id}")
    public String editarProfessor(@PathVariable Long id, @ModelAttribute Professor professor) {
        Professor professorExistente = professorService.procurar(id);
        BeanUtils.copyProperties(professor, professorExistente, "id");
        professorService.salvar(professorExistente);
        return "redirect:/professores/listar";
    }

    @GetMapping("/remover/{id}")
    public String removerProfessor(@PathVariable Long id) {
        professorService.remover(id);
        return "redirect:/professores/listar";
    }
}
