package com.example.Everest.Refrigeracao.controller;

import com.example.Everest.Refrigeracao.dto.MensagemChatRequestDTO;
import com.example.Everest.Refrigeracao.dto.MensagemChatResponseDTO;
import com.example.Everest.Refrigeracao.service.MensagemChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MensagemChatController {
    @Autowired
    private MensagemChatService service;

    public MensagemChatController(MensagemChatService service) {
        this.service = service;
    }

    @GetMapping("/locacao/{locacaoId}")
    public ResponseEntity<List<MensagemChatResponseDTO>> listarPorLocacao(@PathVariable Long locacaoId) {
        return ResponseEntity.ok(service.listarPorLocacao(locacaoId));
    }

    @PostMapping
    public ResponseEntity<MensagemChatResponseDTO> enviar(@Valid @RequestBody MensagemChatRequestDTO mensagemChatRequestDTO) {
        return ResponseEntity.ok(service.enviar(mensagemChatRequestDTO));
    }

}
