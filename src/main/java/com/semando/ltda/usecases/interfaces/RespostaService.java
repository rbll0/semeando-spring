package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.RespostaRequest;
import com.semando.ltda.gateways.responses.RespostaResponse;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Interface que define os serviços relacionados às respostas dos usuários.
 * Contém os métodos para criar, atualizar, buscar e deletar respostas.
 */
@Service
public interface RespostaService {
    RespostaResponse criarResposta(RespostaRequest request);

    RespostaResponse atualizarResposta(Long id, RespostaRequest request);

    RespostaResponse buscarRespostaPorId(Long id);

    List<RespostaResponse> buscarRespostas();

    List<RespostaResponse> buscarRespostasPorUsuario(Long usuarioId);

    void deletarResposta(Long id);
}
