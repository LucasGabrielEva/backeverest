package com.example.Everest.Refrigeracao.dto;

import com.example.Everest.Refrigeracao.model.MensagemChat;

import java.time.LocalDateTime;

public class MensagemChatResponseDTO {
    private Long id;
    private UsuarioResponseDTO Remetente;
    private Long locacaoId;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private Boolean lida;

    public MensagemChatResponseDTO() {}

    public static MensagemChatResponseDTO from(MensagemChat mensagemChat) {
        MensagemChatResponseDTO dto = new MensagemChatResponseDTO();
        dto.id = mensagemChat.getId();
        dto.locacaoId = mensagemChat.getLocacao().getId();
        dto.mensagem = mensagemChat.getMensagem();
        dto.dataEnvio = mensagemChat.getDataEnvio();
        dto.lida = mensagemChat.isLida();
        return dto;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getLocacaoId() {return locacaoId;}
    public void setLocacaoId(Long locacaoId) {this.locacaoId = locacaoId;}
    public UsuarioResponseDTO getRemetente() {return Remetente;}
    public void setRemetente(UsuarioResponseDTO remetente) {this.Remetente = remetente;}
    public String getMensagem() {return mensagem;}
    public void setMensagem(String mensagem) {this.mensagem = mensagem;}
    public LocalDateTime getDataEnvio() {return dataEnvio;}
    public void setDataEnvio(LocalDateTime dataEnvio) {this.dataEnvio = dataEnvio;}




}
