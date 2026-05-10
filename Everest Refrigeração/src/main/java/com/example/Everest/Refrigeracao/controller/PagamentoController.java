package com.example.Everest.Refrigeracao.controller;

import com.example.Everest.Refrigeracao.dto.PagamentoRequestDTO;
import com.example.Everest.Refrigeracao.dto.PagamentoResponseDTO;
import com.example.Everest.Refrigeracao.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PagamentoController {
    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> cadastrar(@RequestBody @Valid PagamentoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/locacao/{locacaoId}")
    public ResponseEntity<List<PagamentoResponseDTO>> buscarPorLocacao(@PathVariable Long locacaoId) {
        return ResponseEntity.ok(service.buscarPorLocacao(locacaoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
