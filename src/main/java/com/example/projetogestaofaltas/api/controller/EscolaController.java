package com.example.projetogestaofaltas.api.controller;

import com.example.projetogestaofaltas.core.service.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    @Autowired
    private EscolaService escolaService;

    @GetMapping
    public ResponseEntity<?> listarEscolas(){
        return ResponseEntity.ok(escolaService.listar());
    }
}
