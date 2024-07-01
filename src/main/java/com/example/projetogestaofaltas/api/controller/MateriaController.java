package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Materia;
import com.example.projetogestaofaltas.core.service.MateriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // Listar todas as matérias
    @GetMapping
    public ResponseEntity<List<Materia>> listarMaterias() {
        List<Materia> materias = materiaService.listar();
        return ResponseEntity.ok(materias);
    }

    // Obter uma matéria pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Materia> obterMateriaPorId(@PathVariable Long id) {
        Materia materia = materiaService.procurar(id);
        if (materia != null) {
            return ResponseEntity.ok(materia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar uma nova matéria
    @PostMapping
    public ResponseEntity<Materia> adicionarMateria(@RequestBody Materia materia) {
        Materia novaMateria = materiaService.salvar(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMateria);
    }

    // Atualizar uma matéria existente
    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable Long id, @RequestBody Materia materiaAtualizada) {
        Materia materiaExistente = materiaService.procurar(id);
        if (materiaExistente != null) {
            // Copiar todas as propriedades da matériaAtualizada para a matériaExistente, exceto o ID
            BeanUtils.copyProperties(materiaAtualizada, materiaExistente, "id");

            // Salvar a matéria atualizada
            Materia materiaAtualizadaSalva = materiaService.salvar(materiaExistente);
            return ResponseEntity.ok(materiaAtualizadaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Remover uma matéria pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerMateria(@PathVariable Long id) {
        Materia materia = materiaService.procurar(id);
        if (materia != null) {
            materiaService.remover(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
