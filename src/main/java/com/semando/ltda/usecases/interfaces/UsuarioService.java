package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.UsuarioRequest;
import com.semando.ltda.gateways.responses.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface que define os serviços relacionados aos usuários.
 * Contém os métodos para criar, atualizar, buscar e deletar usuários.
 */
@Service
public interface UsuarioService {
    UsuarioResponse criarUsuario(UsuarioRequest request);

    UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request);

    UsuarioResponse buscarUsuarioPorId(Long id);

    List<UsuarioResponse> buscarUsuarios();

    void deletarUsuario(Long id);
}
