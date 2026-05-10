package com.example.Everest.Refrigeracao.controller;


import com.example.Everest.Refrigeracao.dto.ContratoRequestDTO;
import com.example.Everest.Refrigeracao.dto.ContratoResponseDTO;
import com.example.Everest.Refrigeracao.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ContratoController {
    @Autowired
    private ContratoService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratoResponseDTO> gerarContrato(
            @RequestPart("dados") @Valid ContratoRequestDTO request,
            @RequestPart("arquivo") MultipartFile pdf) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request, pdf));
    }
}
