package com.example.Everest.Refrigeracao.controller;

import com.example.Everest.Refrigeracao.dto.LocacaoRequestDTO;
import com.example.Everest.Refrigeracao.dto.LocacaoResponseDTO;
import com.example.Everest.Refrigeracao.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocacaoController {

    @Autowired
    private LocacaoService service;


    @PostMapping
    public ResponseEntity<LocacaoResponseDTO> cadastrar(@RequestBody @Valid LocacaoRequestDTO request) {
        LocacaoResponseDTO response = service.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<LocacaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<LocacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LocacaoResponseDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId));
    }


    @GetMapping("/buscar-cliente")
    public ResponseEntity<List<LocacaoResponseDTO>> buscarPorNomeCliente(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNomeCliente(nome));
    }


    @PutMapping("/{id}")
    public ResponseEntity<LocacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LocacaoRequestDTO request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
