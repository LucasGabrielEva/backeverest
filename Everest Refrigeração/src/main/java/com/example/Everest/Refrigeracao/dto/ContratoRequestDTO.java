package com.example.Everest.Refrigeracao.dto;

import jakarta.validation.constraints.NotNull;

public class ContratoRequestDTO {
    @NotNull(message = "O ID da locação é obrigatório.")
    private Long localId;

    private String termos;

    public ContratoRequestDTO() {
    }

    public ContratoRequestDTO(Long localId, String termos) {
        this.localId = localId;
        this.termos = termos;
    }

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    public String getTermos() {
        return termos;
    }

    public void setTermos(String termos) {
        this.termos = termos;
    }
}
