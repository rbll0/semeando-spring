package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Usuario;
import com.semando.ltda.gateways.procedures.UsuarioProcedures;
import com.semando.ltda.gateways.repositories.UsuarioRepository;
import com.semando.ltda.gateways.requests.UsuarioRequest;
import com.semando.ltda.gateways.responses.UsuarioResponse;
import com.semando.ltda.usecases.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Usuario. Contém a lógica
 * para criar, atualizar, buscar e deletar usuários.
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioProcedures usuarioProcedures;

    @Override
    public UsuarioResponse criarUsuario(UsuarioRequest request) {
        usuarioProcedures.inserirUsuario(
                request.getNomeUsuario(),
                request.getEmail(),
                request.getRanking().toString(),
                request.getBio()
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado após inserção."));
        return mapToResponse(usuario);
    }

    @Override
    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request) {
        usuarioProcedures.atualizarUsuario(
                id,
                request.getNomeUsuario(),
                request.getEmail(),
                request.getRanking().toString(),
                request.getStreak(),
                request.getBio()
        );

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + id));
        return mapToResponse(usuario);
    }

    @Override
    public void deletarUsuario(Long id) {
        usuarioProcedures.deletarUsuario(id);
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponse buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + id));
        return mapToResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> buscarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponse mapToResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setIdUsuario(usuario.getIdUsuario());
        response.setNomeUsuario(usuario.getNomeUsuario());
        response.setEmail(usuario.getEmail());
        response.setRanking(usuario.getRanking());
        response.setStreak(usuario.getStreak());
        response.setBio(usuario.getBio());
        return response;
    }
}
