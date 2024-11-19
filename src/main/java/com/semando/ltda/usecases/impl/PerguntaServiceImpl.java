package com.semando.ltda.usecases.impl;


import com.semando.ltda.domains.Pergunta;
import com.semando.ltda.gateways.procedures.PerguntaProcedures;
import com.semando.ltda.gateways.repositories.LevelRepository;
import com.semando.ltda.gateways.repositories.PerguntaRepository;
import com.semando.ltda.gateways.requests.PerguntaRequest;
import com.semando.ltda.gateways.responses.PerguntaResponse;
import com.semando.ltda.usecases.interfaces.PerguntaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerguntaServiceImpl implements PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final PerguntaProcedures perguntaProcedures;

    @Override
    public PerguntaResponse criarPergunta(PerguntaRequest request) {
        // Chama a procedure para inserir a pergunta
        perguntaProcedures.inserirPergunta(
                request.getIdLevel(),
                request.getTexto(),
                request.getTipoPergunta().toString()
        );

        // Busca a pergunta inserida para retornar no response
        Pergunta pergunta = perguntaRepository.findTopByLevel_IdLevelAndTextoOrderByIdPerguntaDesc(
                request.getIdLevel(),
                request.getTexto()
        ).orElseThrow(() -> new NoSuchElementException("Pergunta não encontrada após inserção."));
        return mapToResponse(pergunta);
    }

    @Override
    public PerguntaResponse atualizarPergunta(Long id, PerguntaRequest request) {
        // Chama a procedure para atualizar a pergunta
        perguntaProcedures.atualizarPergunta(
                id,
                request.getIdLevel(),
                request.getTexto(),
                request.getTipoPergunta().toString()
        );

        // Busca a pergunta atualizada para retornar no response
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pergunta não encontrada com ID: " + id));
        return mapToResponse(pergunta);
    }

    @Override
    public PerguntaResponse buscarPerguntaPorId(Long id) {
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pergunta não encontrada com ID: " + id));
        return mapToResponse(pergunta);
    }

    @Override
    public List<PerguntaResponse> buscarPerguntas() {
        return perguntaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PerguntaResponse> buscarPerguntasPaginadas(Pageable pageable) {
        return perguntaRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public List<PerguntaResponse> buscarPerguntasPorLevel(Long levelId) {
        return perguntaRepository.findByLevel_IdLevel(levelId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PerguntaResponse> buscarPerguntasPorLevelPaginadas(Long levelId, Pageable pageable) {
        return perguntaRepository.findByLevel_IdLevel(levelId, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public void deletarPergunta(Long id) {
        // Chama a procedure para deletar a pergunta
        perguntaProcedures.deletarPergunta(id);

        // Deleta a pergunta do repositório para manter consistência
        perguntaRepository.deleteById(id);
    }

    private PerguntaResponse mapToResponse(Pergunta pergunta) {
        PerguntaResponse response = new PerguntaResponse();
        response.setIdPergunta(pergunta.getIdPergunta());
        response.setIdLevel(pergunta.getLevel().getIdLevel());
        response.setTexto(pergunta.getTexto());
        response.setTipoPergunta(pergunta.getTipoPergunta());
        return response;
    }
}
