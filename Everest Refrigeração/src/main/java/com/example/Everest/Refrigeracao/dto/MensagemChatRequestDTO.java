package com.example.Everest.Refrigeracao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MensagemChatRequestDTO {
    @NotNull(message = "O ID do remetente é obrigatório")
    private Long remetenteId;

    @NotNull(message = "O ID da locação é obrigatório")
    private Long locacaoId;

    @NotBlank(message = "A mensagem não pode estar vazia")
    private String mensagem;

    public MensagemChatRequestDTO() {
    }

    public MensagemChatRequestDTO(Long remetenteId, Long locacaoId, String mensagem) {
        this.remetenteId = remetenteId;
        this.locacaoId = locacaoId;
        this.mensagem = mensagem;
    }

    public Long getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(Long remetenteId) {
        this.remetenteId = remetenteId;
    }

    public Long getLocacaoId() {
        return locacaoId;
    }

    public void setLocacaoId(Long locacaoId) {
        this.locacaoId = locacaoId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
