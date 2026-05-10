package com.example.Everest.Refrigeracao.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class LocacaoRequestDTO {
    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long usuarioId;

    // Novo campo adicionado
    private String nomeUsuario;

    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início não pode ser no passado.")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória.")
    private LocalDate dataFim;

    @NotEmpty(message = "A locação deve ter pelo menos um equipamento.")
    private List<Long> equipamentosIds;

    public LocacaoRequestDTO() {
    }

    public LocacaoRequestDTO(Long usuarioId, String nomeUsuario, LocalDate dataInicio, LocalDate dataFim, List<Long> equipamentosIds) {
        this.usuarioId = usuarioId;
        this.nomeUsuario = nomeUsuario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.equipamentosIds = equipamentosIds;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<Long> getEquipamentosIds() {
        return equipamentosIds;
    }

    public void setEquipamentosIds(List<Long> equipamentosIds) {
        this.equipamentosIds = equipamentosIds;
    }
}
