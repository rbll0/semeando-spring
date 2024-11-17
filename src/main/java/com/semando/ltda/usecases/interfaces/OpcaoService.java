package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.OpcaoRequest;
import com.semando.ltda.gateways.responses.OpcaoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface que define os serviços relacionados às opções de resposta.
 * Contém os métodos para criar, atualizar, buscar e deletar opções.
 */
@Service
public interface OpcaoService {
    OpcaoResponse criarOpcao(OpcaoRequest request);

    OpcaoResponse atualizarOpcao(Long id, OpcaoRequest request);

    OpcaoResponse buscarOpcaoPorId(Long id);

    List<OpcaoResponse> buscarOpcoes();

    List<OpcaoResponse> buscarOpcoesPorPergunta(Long perguntaId);

    void deletarOpcao(Long id);
}
