package com.example.Everest.Refrigeracao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PagamentoRequestDTO {
    @NotNull(message = "O ID da locação é obrigatório.")
    private Long locacaoId;

    @NotNull(message = "O valor do pagamento é obrigatório.")
    @Positive(message = "O valor deve ser maior que zero.")
    private BigDecimal valor;

    @NotNull(message = "A forma de pagamento é obrigatória (PIX, CARTAO_CREDITO, etc).")
    private String formaPagamento;

    @NotNull(message = "O status do pagamento é obrigatório (PENDENTE, APROVADO, etc).")
    private String status;

    public PagamentoRequestDTO() {
    }

    public PagamentoRequestDTO(Long locacaoId, BigDecimal valor, String formaPagamento, String status) {
        this.locacaoId = locacaoId;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.status = status;
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

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
