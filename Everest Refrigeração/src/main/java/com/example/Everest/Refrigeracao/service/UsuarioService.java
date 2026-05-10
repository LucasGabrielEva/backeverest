package com.example.Everest.Refrigeracao.service;

import com.example.Everest.Refrigeracao.dto.UsuarioRequestDTO;
import com.example.Everest.Refrigeracao.dto.UsuarioResponseDTO;
import com.example.Everest.Refrigeracao.model.Usuario;
import com.example.Everest.Refrigeracao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE: Salvar novo usuário
    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }
        if (request.getCpf() != null && !request.getCpf().isEmpty() && repository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema.");
        }
        if (request.getCnpj() != null && !request.getCnpj().isEmpty() && repository.existsByCnpj(request.getCnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado no sistema.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setCpf(request.getCpf());
        usuario.setCnpj(request.getCnpj());
        usuario.setTelefone(request.getTelefone());
        usuario.setTipo(request.getTipo());

        Usuario salvo = repository.save(usuario);
        return mapearParaResponse(salvo);
    }

    // READ ALL: Listar todos
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID: Buscar por ID
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        return mapearParaResponse(usuario);
    }

    // READ BY NOME: Buscar por Nome (traz uma lista, pois podem haver vários nomes parecidos)
    public List<UsuarioResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    // UPDATE: Atualizar usuário
    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Atualiza os dados (ignoramos a senha aqui por segurança)
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setCpf(request.getCpf());
        usuario.setCnpj(request.getCnpj());
        usuario.setTelefone(request.getTelefone());
        usuario.setTipo(request.getTipo());

        Usuario atualizado = repository.save(usuario);
        return mapearParaResponse(atualizado);
    }

    // DELETE: Deletar usuário
    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        repository.deleteById(id);
    }

    // Metodo de mapeamento manual
    private UsuarioResponseDTO mapearParaResponse(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCpf(u.getCpf());
        dto.setCnpj(u.getCnpj());
        dto.setTelefone(u.getTelefone());
        dto.setTipo(u.getTipo().name());
        return dto;
    }

}
