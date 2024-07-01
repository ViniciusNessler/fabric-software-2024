package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.domain.model.Aluno;
import com.example.projetogestaofaltas.core.service.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
//    @Autowired
//    AlunoService alunoService;
//
//    @GetMapping
//    public ResponseEntity<?> listarAlunos(){
//        return ResponseEntity.ok(alunoService.listar());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Aluno> buscarAluno(@Param("id") Long id){
//        return ResponseEntity.ok(alunoService.procurar(id));
//    }
//
//    @PutMapping
//    public ResponseEntity<Aluno> salvarAluno(Aluno aluno){
//        alunoService.salvar(aluno);
//        return ResponseEntity.ok(aluno);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> removerAluno(@Param("id") Long id){
//        alunoService.remover(id);
//        return ResponseEntity.ok().build();
//    }

    @Autowired
    private AlunoService alunoService;

    // Listar todos os alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listar();
        return ResponseEntity.ok(alunos);
    }

    // Buscar um aluno pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.procurar(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Salvar ou atualizar um aluno
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> salvarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoService.procurar(id);
        if (alunoExistente != null) {
            // Copiar todas as propriedades do alunoAtualizado para o alunoExistente, exceto o ID
            BeanUtils.copyProperties(alunoAtualizado, alunoExistente, "id");

            // Salvar o aluno atualizado
            Aluno alunoSalvo = alunoService.salvar(alunoExistente);
            return ResponseEntity.ok(alunoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Remover um aluno pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.procurar(id);
        if (aluno != null) {
            alunoService.remover(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
