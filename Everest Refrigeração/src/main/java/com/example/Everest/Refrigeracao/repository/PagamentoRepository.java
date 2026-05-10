package com.example.Everest.Refrigeracao.repository;

import com.example.Everest.Refrigeracao.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByLocacaoId(Long locacaoId);
}
