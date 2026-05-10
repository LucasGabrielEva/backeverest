package com.example.Everest.Refrigeracao.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contratos")
public class Contrato {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "locacao_id", nullable = false )
        private Locacao locacao;

        private LocalDateTime dataCriacao;

        private String arquivoPdf;

        @Column(columnDefinition = "TEXT")
        private String termos;

    public Contrato() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Contrato(Long id, Locacao locacao, LocalDateTime dataCriacao, String arquivoPdf, String termos) {
        this.id = id;
        this.locacao = locacao;
        this.dataCriacao = dataCriacao;
        this.arquivoPdf = arquivoPdf;
        this.termos = termos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getArquivoPdf() {
        return arquivoPdf;
    }

    public void setArquivoPdf(String arquivoPdf) {
        this.arquivoPdf = arquivoPdf;
    }

    public String getTermos() {
        return termos;
    }

    public void setTermos(String termos) {
        this.termos = termos;
    }
}
