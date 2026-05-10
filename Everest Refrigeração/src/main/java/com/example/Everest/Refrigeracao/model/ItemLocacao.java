package com.example.Everest.Refrigeracao.model;

import jakarta.persistence.*;

@Entity
public class ItemLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Locacao_id", nullable = false)
    private Locacao locaoao;

    @ManyToOne
    @JoinColumn(name = "equipamentos_id", nullable = false)
    private Equipamento equipamento;

    private Integer quantidade;

    public ItemLocacao() {
    }

    public ItemLocacao(Long id, Locacao locaoao, Equipamento equipamento, Integer quantidade) {
        this.id = id;
        this.locaoao = locaoao;
        this.equipamento = equipamento;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locacao getLocaoao() {
        return locaoao;
    }

    public void setLocaoao(Locacao locaoao) {
        this.locaoao = locaoao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
