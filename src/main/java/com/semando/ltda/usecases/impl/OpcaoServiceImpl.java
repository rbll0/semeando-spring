package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Opcao;
import com.semando.ltda.gateways.repositories.OpcaoRepository;
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

    @Override
    public OpcaoResponse criarOpcao(OpcaoRequest request) {
        Opcao opcao = new Opcao();
        opcao.setIdPergunta(request.getIdPergunta());
        opcao.setTexto(request.getTexto());
        opcao.setCorreta(request.getCorreta());
        opcao = opcaoRepository.save(opcao);
        return mapToResponse(opcao);
    }

    @Override
    public OpcaoResponse atualizarOpcao(Long id, OpcaoRequest request) {
        Opcao opcao = opcaoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + id));
        opcao.setTexto(request.getTexto());
        opcao.setCorreta(request.getCorreta());
        opcao = opcaoRepository.save(opcao);
        return mapToResponse(opcao);
    }

    @Override
    public OpcaoResponse buscarOpcaoPorId(Long id) {
        Opcao opcao = opcaoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + id));
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
    public void deletarOpcao(Long id) {
        opcaoRepository.deleteById(id);
    }

    private OpcaoResponse mapToResponse(Opcao opcao) {
        OpcaoResponse response = new OpcaoResponse();
        response.setIdOpcao(opcao.getIdOpcao());
        response.setIdPergunta(opcao.getIdPergunta());
        response.setTexto(opcao.getTexto());
        response.setCorreta(opcao.getCorreta());
        return response;
    }
}
