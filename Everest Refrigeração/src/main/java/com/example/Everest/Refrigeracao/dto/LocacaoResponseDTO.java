package com.example.Everest.Refrigeracao.dto;

import java.time.LocalDate;
import java.util.List;

public class LocacaoResponseDTO {
    private Long id;
    private String nomeUsuario;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private List<EquipamentoResponseDTO> equipamentos;

    public LocacaoResponseDTO() {
    }

    public LocacaoResponseDTO(Long id, String nomeUsuario, LocalDate dataInicio, LocalDate dataFim, String status, List<EquipamentoResponseDTO> equipamentos) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.equipamentos = equipamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<EquipamentoResponseDTO> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoResponseDTO> equipamentos) {
        this.equipamentos = equipamentos;
    }
}


