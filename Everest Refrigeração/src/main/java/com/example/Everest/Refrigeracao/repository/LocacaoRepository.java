package com.example.Everest.Refrigeracao.repository;

import com.example.Everest.Refrigeracao.model.Locacao;
import com.example.Everest.Refrigeracao.model.StatusLocacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    // Busca todas as locações de um cliente pelo ID
    List<Locacao> findByUsuarioId(Long usuarioId);

    // NOVA ROTA: Busca locações pelo NOME do cliente (ignorando maiúsculas/minúsculas)
    List<Locacao> findByUsuarioNomeContainingIgnoreCase(String nome);

    // Busca locações por status (ex: ATIVA, FINALIZADA)
    List<Locacao> findByStatus(StatusLocacao status);
}
