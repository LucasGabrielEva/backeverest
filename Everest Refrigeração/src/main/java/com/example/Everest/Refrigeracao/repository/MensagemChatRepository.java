package com.example.Everest.Refrigeracao.repository;

import com.example.Everest.Refrigeracao.model.MensagemChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemChatRepository extends JpaRepository<MensagemChat, Long> {
    List<MensagemChat> findByLocacaoIdOrderByDataEnvioAsc(Long locacaoId);
}
