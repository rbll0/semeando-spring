package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    // Método customizado para buscar um usuário por email
    Optional<Usuario> findByEmail(String email);
}
