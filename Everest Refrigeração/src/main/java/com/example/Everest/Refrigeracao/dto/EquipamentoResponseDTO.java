package com.example.Everest.Refrigeracao.dto;

import java.math.BigDecimal;

public class EquipamentoResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal valorDiaria;
    private Boolean disponivel;

    public EquipamentoResponseDTO() {
    }

    public EquipamentoResponseDTO(Long id, String nome, BigDecimal valorDiaria, Boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
