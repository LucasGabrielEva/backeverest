package com.example.Everest.Refrigeracao.repository;

import com.example.Everest.Refrigeracao.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    Optional<Contrato> findByLocacaoId(Long locacaoId);
}
