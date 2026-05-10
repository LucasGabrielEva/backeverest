package com.example.Everest.Refrigeracao.service;

import com.example.Everest.Refrigeracao.dto.MensagemChatRequestDTO;
import com.example.Everest.Refrigeracao.dto.MensagemChatResponseDTO;
import com.example.Everest.Refrigeracao.model.Locacao;
import com.example.Everest.Refrigeracao.model.MensagemChat;
import com.example.Everest.Refrigeracao.model.Usuario;
import com.example.Everest.Refrigeracao.repository.LocacaoRepository;
import com.example.Everest.Refrigeracao.repository.MensagemChatRepository;
import com.example.Everest.Refrigeracao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensagemChatService {

    @Autowired
    private MensagemChatRepository mensagemChatRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public MensagemChatService(
            MensagemChatRepository mensagemChatRepository,
            LocacaoRepository locacaoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.mensagemChatRepository = mensagemChatRepository;
        this.locacaoRepository = locacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<MensagemChatResponseDTO> listarPorLocacao(Long locacaoId) {

        return mensagemChatRepository
                .findByLocacaoIdOrderByDataEnvioAsc(locacaoId)
                .stream()
                .map(MensagemChatResponseDTO::from)
                .collect(Collectors.toList());
    }

    public MensagemChatResponseDTO enviar(MensagemChatRequestDTO dto) {

        Locacao locacao = locacaoRepository
                .findById(dto.getLocacaoId())
                .orElseThrow(() -> new RuntimeException(
                        "Locação não encontrada com ID: " + dto.getLocacaoId()
                ));

        Usuario remetente = usuarioRepository
                .findById(dto.getRemetenteId())
                .orElseThrow(() -> new RuntimeException(
                        "Usuário não encontrado com ID: " + dto.getRemetenteId()
                ));

        MensagemChat msg = new MensagemChat();

        msg.setLocacao(locacao);
        msg.setRemetente(remetente);
        msg.setMensagem(dto.getMensagem());

        MensagemChat mensagemSalva = mensagemChatRepository.save(msg);

        return MensagemChatResponseDTO.from(mensagemSalva);
    }




}
