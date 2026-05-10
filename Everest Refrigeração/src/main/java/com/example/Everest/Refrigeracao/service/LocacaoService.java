package com.example.Everest.Refrigeracao.service;

import com.example.Everest.Refrigeracao.dto.EquipamentoResponseDTO;
import com.example.Everest.Refrigeracao.dto.LocacaoRequestDTO;
import com.example.Everest.Refrigeracao.dto.LocacaoResponseDTO;
import com.example.Everest.Refrigeracao.model.*;
import com.example.Everest.Refrigeracao.repository.EquipamentoRepository;
import com.example.Everest.Refrigeracao.repository.LocacaoRepository;
import com.example.Everest.Refrigeracao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocacaoService {
    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREATE: Salvar nova locação com validação extra de nome
    @Transactional
    public LocacaoResponseDTO salvar(LocacaoRequestDTO request) {
        // 1. Procura o utilizador por ID
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Utilizador não encontrado."));

        // 2. VALIDAÇÃO EXTRA: Compara o nome enviado com o nome no banco de dados
        if (request.getNomeUsuario() != null && !request.getNomeUsuario().isBlank()) {
            if (!usuario.getNome().equalsIgnoreCase(request.getNomeUsuario().trim())) {
                throw new IllegalArgumentException("Erro de consistência: O nome '" + request.getNomeUsuario()
                        + "' não corresponde ao utilizador registado com o ID " + request.getUsuarioId());
            }
        }

        // 3. Validação de datas
        if (request.getDataInicio().isAfter(request.getDataFim())) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setDataInicio(request.getDataInicio());
        locacao.setDataFim(request.getDataFim());
        locacao.setStatus(StatusLocacao.ATIVA);

        // 4. Processamento dos itens/equipamentos
        List<ItemLocacao> itens = request.getEquipamentosIds().stream().map(equipId -> {
            Equipamento equip = equipamentoRepository.findById(equipId)
                    .orElseThrow(() -> new IllegalArgumentException("Equipamento com ID " + equipId + " não encontrado."));

            if (!equip.getDisponivel()) {
                throw new IllegalArgumentException("O equipamento '" + equip.getNome() + "' já está alugado.");
            }

            // Atualiza disponibilidade
            equip.setDisponivel(false);
            equipamentoRepository.save(equip);

            ItemLocacao item = new ItemLocacao();
            item.setLocaoao(locacao);
            item.setEquipamento(equip);
            item.setQuantidade(1);

            return item;
        }).collect(Collectors.toList());

        locacao.setItens(itens);
        Locacao locacaoSalva = locacaoRepository.save(locacao);

        return mapearParaResponse(locacaoSalva);
    }

    // READ ALL
    public List<LocacaoResponseDTO> listarTodos() {
        return locacaoRepository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public LocacaoResponseDTO buscarPorId(Long id) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada."));
        return mapearParaResponse(locacao);
    }

    // READ BY USUARIO ID
    public List<LocacaoResponseDTO> buscarPorUsuario(Long usuarioId) {
        return locacaoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    // READ BY NOME DO CLIENTE
    public List<LocacaoResponseDTO> buscarPorNomeCliente(String nome) {
        return locacaoRepository.findByUsuarioNomeContainingIgnoreCase(nome).stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    // UPDATE: Atualizar apenas as datas
    @Transactional
    public LocacaoResponseDTO atualizar(Long id, LocacaoRequestDTO request) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada."));

        if (request.getDataInicio().isAfter(request.getDataFim())) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }

        locacao.setDataInicio(request.getDataInicio());
        locacao.setDataFim(request.getDataFim());

        Locacao locacaoAtualizada = locacaoRepository.save(locacao);
        return mapearParaResponse(locacaoAtualizada);
    }

    // DELETE: Libera equipamentos e remove a locação
    @Transactional
    public void deletar(Long id) {
        Locacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada."));

        for (ItemLocacao item : locacao.getItens()) {
            Equipamento equip = item.getEquipamento();
            equip.setDisponivel(true);
            equipamentoRepository.save(equip);
        }

        locacaoRepository.deleteById(id);
    }

    // Mapeamento manual para DTO
    private LocacaoResponseDTO mapearParaResponse(Locacao locacao) {
        List<EquipamentoResponseDTO> equipamentosDTO = locacao.getItens().stream()
                .map(item -> {
                    EquipamentoResponseDTO dto = new EquipamentoResponseDTO();
                    dto.setId(item.getEquipamento().getId());
                    dto.setNome(item.getEquipamento().getNome());
                    dto.setValorDiaria(item.getEquipamento().getValorDiaria());
                    dto.setDisponivel(item.getEquipamento().getDisponivel());
                    return dto;
                })
                .collect(Collectors.toList());

        LocacaoResponseDTO response = new LocacaoResponseDTO();
        response.setId(locacao.getId());
        response.setNomeUsuario(locacao.getUsuario().getNome());
        response.setDataInicio(locacao.getDataInicio());
        response.setDataFim(locacao.getDataFim());
        response.setStatus(locacao.getStatus().name());
        response.setEquipamentos(equipamentosDTO);

        return response;
    }
}
