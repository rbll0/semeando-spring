package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.PerguntaRequest;
import com.semando.ltda.gateways.responses.PerguntaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<PerguntaResponse> buscarPerguntasPaginadas(Pageable pageable); // Adicionado para paginação

    List<PerguntaResponse> buscarPerguntasPorLevel(Long levelId);

    Page<PerguntaResponse> buscarPerguntasPorLevelPaginadas(Long levelId, Pageable pageable); // Adicionado para paginação


    void deletarPergunta(Long id);
}
