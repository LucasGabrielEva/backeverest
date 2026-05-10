package com.example.Everest.Refrigeracao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class EquipamentoRequestDTO {
    @NotBlank(message = "O nome do equipamento é obrigatório.")
    private String nome;

    private String descricao;

    @NotNull(message = "O valor da diária é obrigatório.")
    @Positive(message = "O valor da diária deve ser maior que zero.")
    private BigDecimal valorDiaria;

    public EquipamentoRequestDTO() {
    }

    public EquipamentoRequestDTO(String nome, String descricao, BigDecimal valorDiaria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
