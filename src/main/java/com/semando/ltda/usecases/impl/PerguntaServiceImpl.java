package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Level;
import com.semando.ltda.domains.Pergunta;
import com.semando.ltda.gateways.repositories.LevelRepository;
import com.semando.ltda.gateways.repositories.PerguntaRepository;
import com.semando.ltda.gateways.requests.PerguntaRequest;
import com.semando.ltda.gateways.responses.PerguntaResponse;
import com.semando.ltda.usecases.interfaces.PerguntaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerguntaServiceImpl implements PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final LevelRepository levelRepository;


    @Override
    public PerguntaResponse criarPergunta(PerguntaRequest request) {
        Pergunta pergunta = new Pergunta();
        Level level = levelRepository.findById(request.getIdLevel())
                .orElseThrow(() -> new NoSuchElementException("Level não encontrado com ID: " + request.getIdLevel()));

        pergunta.setLevel(level);
        pergunta.setTexto(request.getTexto());
        pergunta.setTipoPergunta(request.getTipoPergunta());
        pergunta = perguntaRepository.save(pergunta);
        return mapToResponse(pergunta);
    }

    @Override
    public PerguntaResponse atualizarPergunta(Long id, PerguntaRequest request) {
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pergunta não encontrada com ID: " + id));

        Level level = levelRepository.findById(request.getIdLevel())
                .orElseThrow(() -> new NoSuchElementException("Level não encontrado com ID: " + request.getIdLevel()));

        pergunta.setLevel(level);
        pergunta.setTexto(request.getTexto());
        pergunta.setTipoPergunta(request.getTipoPergunta());
        pergunta = perguntaRepository.save(pergunta);
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
    public List<PerguntaResponse> buscarPerguntasPorLevel(Long levelId) {
        return perguntaRepository.findByLevelIdLevel(levelId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPergunta(Long id) {
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
