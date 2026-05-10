package com.example.Everest.Refrigeracao.repository;

import com.example.Everest.Refrigeracao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Busca por email
    Optional<Usuario> findByEmail(String email);

    //Buscar por cpf(identificação de pessoa fisica)
    Optional<Usuario> findByCpf(String cpf);

    //Buscar por cnpj(identificação de pessoa juridica)
    Optional<Usuario> findByCnpj(String cnpj);

    //Buscar por Telefone
    Optional<Usuario> findByTelefone(String telefone);

    //Busca por parte do nome(útil para filtros de busca na tela de clientes )
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    //Verifação de existência(úteis para não deixar cadastro duplicado)
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByCnpj(String cnpj);
}
