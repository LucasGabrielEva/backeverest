package com.example.Everest.Refrigeracao.repository;

import com.example.Everest.Refrigeracao.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    // Busca equipamentos pelo nome (pesquisa)
    List<Equipamento> findByNomeContainingIgnoreCase(String nome);

    // Busca apenas equipamentos que estão disponíveis
    List<Equipamento> findByDisponivelTrue();
}
