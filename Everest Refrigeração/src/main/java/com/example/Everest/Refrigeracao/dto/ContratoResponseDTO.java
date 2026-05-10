package com.example.Everest.Refrigeracao.dto;

import java.time.LocalDateTime;

public class ContratoResponseDTO {
    private Long id;
    private Long locacaoId;
    private String nomeCliente;
    private String arquivoPdf;
    private LocalDateTime dataCriacao;

    public ContratoResponseDTO() {
    }

    public ContratoResponseDTO(Long id, Long locacaoId, String nomeCliente, String arquivoPdf, LocalDateTime dataCriacao) {
        this.id = id;
        this.locacaoId = locacaoId;
        this.nomeCliente = nomeCliente;
        this.arquivoPdf = arquivoPdf;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocacaoId() {
        return locacaoId;
    }

    public void setLocacaoId(Long locacaoId) {
        this.locacaoId = locacaoId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getArquivoPdf() {
        return arquivoPdf;
    }

    public void setArquivoPdf(String arquivoPdf) {
        this.arquivoPdf = arquivoPdf;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
