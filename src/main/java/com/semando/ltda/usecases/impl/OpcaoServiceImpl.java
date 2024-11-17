package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Opcao;
import com.semando.ltda.domains.OpcaoId;
import com.semando.ltda.domains.Pergunta;
import com.semando.ltda.gateways.repositories.OpcaoRepository;
import com.semando.ltda.gateways.repositories.PerguntaRepository;
import com.semando.ltda.gateways.requests.OpcaoRequest;
import com.semando.ltda.gateways.responses.OpcaoResponse;
import com.semando.ltda.usecases.interfaces.OpcaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Opção. Contém a lógica
 * para criar, atualizar, buscar e deletar opções.
 */
@Service
@RequiredArgsConstructor
public class OpcaoServiceImpl implements OpcaoService {

    private final OpcaoRepository opcaoRepository;
    private final PerguntaRepository perguntaRepository;

    @Override
    public OpcaoResponse criarOpcao(OpcaoRequest request) {
        Opcao opcao = new Opcao();

        Pergunta pergunta = perguntaRepository.findById(request.getIdPergunta())
                .orElseThrow(() -> new NoSuchElementException("Pergunta não encontrada com ID: " + request.getIdPergunta()));
        opcao.setPergunta(pergunta);

        opcao.setTexto(request.getTexto());
        opcao.setOpCorreta(request.getCorreta());
        opcao = opcaoRepository.save(opcao);
        return mapToResponse(opcao);
    }

    @Override
    public OpcaoResponse atualizarOpcao(Integer idOpcao, Long idPergunta, OpcaoRequest request) {
        OpcaoId opcaoId = new OpcaoId(idOpcao, idPergunta); // Cria a chave composta

        Opcao opcao = opcaoRepository.findById(opcaoId)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + opcaoId));

        opcao.setTexto(request.getTexto());
        opcao.setOpCorreta(request.getCorreta());

        opcao = opcaoRepository.save(opcao);
        return mapToResponse(opcao);
    }

    @Override
    public OpcaoResponse buscarOpcaoPorId(Integer idOpcao, Long idPergunta) {
        OpcaoId opcaoId = new OpcaoId(idOpcao, idPergunta);

        Opcao opcao = opcaoRepository.findById(opcaoId)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + opcaoId));

        return mapToResponse(opcao);
    }

    @Override
    public List<OpcaoResponse> buscarOpcoes() {
        return opcaoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OpcaoResponse> buscarOpcoesPorPergunta(Long perguntaId) {
        return opcaoRepository.findByPerguntaId(perguntaId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarOpcao(Integer idOpcao, Long idPergunta) {
        OpcaoId opcaoId = new OpcaoId(idOpcao, idPergunta);

        opcaoRepository.deleteById(opcaoId);
    }

    private OpcaoResponse mapToResponse(Opcao opcao) {
        OpcaoResponse response = new OpcaoResponse();
        response.setIdOpcao(opcao.getIdOpcao());
        response.setIdPergunta(opcao.getPergunta().getIdPergunta());
        response.setTexto(opcao.getTexto());
        response.setCorreta(opcao.getOpCorreta());
        return response;
    }
}
