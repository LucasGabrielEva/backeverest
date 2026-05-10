package com.example.Everest.Refrigeracao.service;

import com.example.Everest.Refrigeracao.dto.ContratoRequestDTO;
import com.example.Everest.Refrigeracao.dto.ContratoResponseDTO;
import com.example.Everest.Refrigeracao.model.Contrato;
import com.example.Everest.Refrigeracao.model.Locacao;
import com.example.Everest.Refrigeracao.repository.ContratoRepository;
import com.example.Everest.Refrigeracao.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository repository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    private final String diretorioUpload = "C:/everest/uploads/contratos/";

    @Transactional
    public ContratoResponseDTO salvar(ContratoRequestDTO request, MultipartFile pdf) throws IOException {
        Locacao locacao = locacaoRepository.findById(request.getLocalId())
                .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada."));

        Contrato contrato = new Contrato();
        contrato.setLocacao(locacao);
        contrato.setTermos(request.getTermos());

        if (pdf != null && !pdf.isEmpty()) {
            contrato.setArquivoPdf(salvarArquivo(pdf));
        }

        Contrato salvo = repository.save(contrato);
        return mapearParaResponse(salvo);
    }

    private String salvarArquivo(MultipartFile arquivo) throws IOException {
        File pasta = new File(diretorioUpload);
        if (!pasta.exists()) pasta.mkdirs();

        String nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
        Path caminho = Paths.get(diretorioUpload + nomeArquivo);
        Files.write(caminho, arquivo.getBytes());
        return nomeArquivo;
    }

    private ContratoResponseDTO mapearParaResponse(Contrato c) {
        ContratoResponseDTO dto = new ContratoResponseDTO();
        dto.setId(c.getId());
        dto.setLocacaoId(c.getLocacao().getId());
        dto.setNomeCliente(c.getLocacao().getUsuario().getNome());
        dto.setArquivoPdf(c.getArquivoPdf());
        dto.setDataCriacao(c.getDataCriacao());
        return dto;
    }

}
