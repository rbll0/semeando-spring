package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Resposta;
import com.semando.ltda.gateways.procedures.RespostaProcedures;
import com.semando.ltda.gateways.repositories.RespostaRepository;
import com.semando.ltda.gateways.requests.RespostaRequest;
import com.semando.ltda.gateways.responses.RespostaResponse;
import com.semando.ltda.usecases.interfaces.RespostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Resposta. Contém a lógica
 * para criar, atualizar, buscar e deletar respostas.
 */
@Service
@RequiredArgsConstructor
public class RespostaServiceImpl implements RespostaService {

    private final RespostaRepository respostaRepository;
    private final RespostaProcedures respostaProcedures;

    @Override
    public RespostaResponse criarResposta(RespostaRequest request) {
        // Chamada à procedure para inserir resposta
        respostaProcedures.inserirResposta(
                request.getIdUsuario(),
                request.getIdPergunta(),
                request.getIdOpcaoEscolhida().intValue()
        );

        // Buscar a resposta criada para retornar no response
        Resposta resposta = respostaRepository.findTopByUsuario_IdUsuarioAndPergunta_IdPerguntaOrderByIdRespostaDesc(
                request.getIdUsuario(),
                request.getIdPergunta()
        ).orElseThrow(() -> new NoSuchElementException("Resposta não encontrada após inserção."));

        return mapToResponse(resposta);
    }

    @Override
    public RespostaResponse atualizarResposta(Long id, RespostaRequest request) {
        // Chamada à procedure para atualizar resposta
        respostaProcedures.atualizarResposta(
                id,
                request.getIdUsuario(),
                request.getIdPergunta(),
                request.getIdOpcaoEscolhida().intValue()
        );

        // Buscar a resposta atualizada para retornar no response
        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resposta não encontrada com ID: " + id));

        return mapToResponse(resposta);
    }

    @Override
    public void deletarResposta(Long id) {
        // Chamada à procedure para deletar resposta
        respostaProcedures.deletarResposta(id);

        // Deletar resposta do repositório para manter consistência
        respostaRepository.deleteById(id);
    }

    @Override
    public RespostaResponse buscarRespostaPorId(Long id) {
        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resposta não encontrada com ID: " + id));
        return mapToResponse(resposta);
    }

    @Override
    public List<RespostaResponse> buscarRespostas() {
        return respostaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RespostaResponse> buscarRespostasPorUsuario(Long usuarioId) {
        return respostaRepository.findByUsuario_IdUsuario(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RespostaResponse mapToResponse(Resposta resposta) {
        RespostaResponse response = new RespostaResponse();
        response.setIdResposta(resposta.getIdResposta());
        response.setIdUsuario(resposta.getUsuario().getIdUsuario());
        response.setIdPergunta(resposta.getPergunta().getIdPergunta());

        // Adiciona verificação para evitar NullPointerException
        if (resposta.getOpEscolhida() != null && resposta.getOpEscolhida().getId() != null) {
            response.setIdOpcaoEscolhida(resposta.getOpEscolhida().getId().getIdOpcao().longValue());
        } else {
            response.setIdOpcaoEscolhida(null); // Define como null se não houver `opEscolhida`
        }

        return response;
    }

}
