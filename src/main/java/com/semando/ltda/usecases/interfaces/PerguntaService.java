package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.PerguntaRequest;
import com.semando.ltda.gateways.responses.PerguntaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface que define os serviços relacionados às perguntas.
 * Contém os métodos para criar, atualizar, buscar e deletar perguntas.
 */
@Service
public interface PerguntaService {
    PerguntaResponse criarPergunta(PerguntaRequest request);

    PerguntaResponse atualizarPergunta(Long id, PerguntaRequest request);

    PerguntaResponse buscarPerguntaPorId(Long id);

    List<PerguntaResponse> buscarPerguntas();

    List<PerguntaResponse> buscarPerguntasPorLevel(Long levelId);

    void deletarPergunta(Long id);
}
