package com.example.Everest.Refrigeracao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoResponseDTO {
    private Long id;
    private Long locacaoId;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String status;
    private String formaPagamento;

    public PagamentoResponseDTO() {
    }

    public PagamentoResponseDTO(Long id, Long locacaoId, BigDecimal valor, LocalDateTime dataPagamento, String status, String formaPagamento) {
        this.id = id;
        this.locacaoId = locacaoId;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.formaPagamento = formaPagamento;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
