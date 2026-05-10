package com.example.Everest.Refrigeracao.service;

import com.example.Everest.Refrigeracao.dto.EquipamentoRequestDTO;
import com.example.Everest.Refrigeracao.dto.EquipamentoResponseDTO;
import com.example.Everest.Refrigeracao.model.Equipamento;
import com.example.Everest.Refrigeracao.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {
    @Autowired
    private EquipamentoRepository repository;

    @Transactional
    public EquipamentoResponseDTO salvar(EquipamentoRequestDTO request) {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(request.getNome());
        equipamento.setDescricao(request.getDescricao());
        equipamento.setValorDiaria(request.getValorDiaria());
        equipamento.setDisponivel(true); // Sempre começa disponível ao cadastrar

        Equipamento salvo = repository.save(equipamento);
        return mapearParaResponse(salvo);
    }

    public List<EquipamentoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    public EquipamentoResponseDTO buscarPorId(Long id) {
        Equipamento equipamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado."));
        return mapearParaResponse(equipamento);
    }

    public List<EquipamentoResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> listarDisponiveis() {
        return repository.findByDisponivelTrue().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EquipamentoResponseDTO atualizar(Long id, EquipamentoRequestDTO request) {
        Equipamento equipamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado."));

        equipamento.setNome(request.getNome());
        equipamento.setDescricao(request.getDescricao());
        equipamento.setValorDiaria(request.getValorDiaria());

        Equipamento atualizado = repository.save(equipamento);
        return mapearParaResponse(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Equipamento não encontrado.");
        }
        repository.deleteById(id);
    }

    private EquipamentoResponseDTO mapearParaResponse(Equipamento e) {
        EquipamentoResponseDTO dto = new EquipamentoResponseDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setValorDiaria(e.getValorDiaria());
        dto.setDisponivel(e.getDisponivel());
        return dto;
    }
}
