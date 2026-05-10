package com.example.Everest.Refrigeracao.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "equipamento")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @Column(nullable = false)
    private Boolean disponivel = true;

    public Equipamento() {
    }

    public Equipamento(Long id, String nome, String descricao, BigDecimal valorDiaria, Boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
