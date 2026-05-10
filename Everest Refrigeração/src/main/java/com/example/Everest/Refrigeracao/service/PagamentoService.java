package com.example.Everest.Refrigeracao.service;

import com.example.Everest.Refrigeracao.dto.PagamentoRequestDTO;
import com.example.Everest.Refrigeracao.dto.PagamentoResponseDTO;
import com.example.Everest.Refrigeracao.model.FormaPagamento;
import com.example.Everest.Refrigeracao.model.Locacao;
import com.example.Everest.Refrigeracao.model.Pagamento;
import com.example.Everest.Refrigeracao.model.StatusPagamento;
import com.example.Everest.Refrigeracao.repository.LocacaoRepository;
import com.example.Everest.Refrigeracao.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Transactional
    public PagamentoResponseDTO salvar(PagamentoRequestDTO request) {
        // Verifica se a locação existe
        Locacao locacao = locacaoRepository.findById(request.getLocacaoId())
                .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada."));

        Pagamento pagamento = new Pagamento();
        pagamento.setLocacao(locacao);
        pagamento.setValor(request.getValor());
        pagamento.setDataPagamento(LocalDateTime.now());

        // Converte Strings do DTO para Enums
        try {
            pagamento.setStatus(StatusPagamento.valueOf(request.getStatus().toUpperCase()));
            pagamento.setFormaPagamento(FormaPagamento.valueOf(request.getFormaPagamento().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status ou Forma de Pagamento inválida.");
        }

        Pagamento salvo = repository.save(pagamento);
        return mapearParaResponse(salvo);
    }

    public List<PagamentoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    public List<PagamentoResponseDTO> buscarPorLocacao(Long locacaoId) {
        return repository.findByLocacaoId(locacaoId).stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Pagamento não encontrado.");
        }
        repository.deleteById(id);
    }

    private PagamentoResponseDTO mapearParaResponse(Pagamento p) {
        PagamentoResponseDTO dto = new PagamentoResponseDTO();
        dto.setId(p.getId());
        dto.setLocacaoId(p.getLocacao().getId());
        dto.setValor(p.getValor());
        dto.setDataPagamento(p.getDataPagamento());
        dto.setStatus(p.getStatus().name());
        dto.setFormaPagamento(p.getFormaPagamento().name());
        return dto;
    }
}
