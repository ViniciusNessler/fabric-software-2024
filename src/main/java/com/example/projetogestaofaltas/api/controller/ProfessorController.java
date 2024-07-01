package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Professor;
import com.example.projetogestaofaltas.core.service.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    // Listar todos os professores
    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores() {
        List<Professor> professores = professorService.listar();
        return ResponseEntity.ok(professores);
    }

    // Buscar um professor pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessor(@PathVariable Long id) {
        Professor professor = professorService.procurar(id);
        if (professor != null) {
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Salvar ou atualizar um professor
    @PutMapping("/{id}")
    public ResponseEntity<Professor> salvarProfessor(@PathVariable Long id, @RequestBody Professor professorAtualizado) {
        Professor professorExistente = professorService.procurar(id);
        if (professorExistente != null) {
            // Copiar todas as propriedades do professorAtualizado para o professorExistente, exceto o ID
            BeanUtils.copyProperties(professorAtualizado, professorExistente, "id");

            // Salvar o professor atualizado
            Professor professorSalvo = professorService.salvar(professorExistente);
            return ResponseEntity.ok(professorSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Remover um professor pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProfessor(@PathVariable Long id) {
        Professor professor = professorService.procurar(id);
        if (professor != null) {
            professorService.remover(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
