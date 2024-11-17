package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Level;
import com.semando.ltda.gateways.repositories.LevelRepository;
import com.semando.ltda.gateways.requests.LevelRequest;
import com.semando.ltda.gateways.responses.LevelResponse;
import com.semando.ltda.usecases.interfaces.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Level. Contém a lógica
 * para criar, atualizar, buscar e deletar níveis.
 */
@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    @Override
    public LevelResponse criarLevel(LevelRequest request) {
        Level level = new Level();
        level.setTitulo(request.getTitulo());
        level.setDescricao(request.getDescricao());
        level.setDificuldade(request.getDificuldade());
        level = levelRepository.save(level);
        return mapToResponse(level);
    }

    @Override
    public LevelResponse atualizarLevel(Long id, LevelRequest request) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nível não encontrado com ID: " + id));
        level.setTitulo(request.getTitulo());
        level.setDescricao(request.getDescricao());
        level.setDificuldade(request.getDificuldade());
        level = levelRepository.save(level);
        return mapToResponse(level);
    }

    @Override
    public void deletarLevel(Long id) {
        levelRepository.deleteById(id);
    }

    @Override
    public LevelResponse buscarLevelPorId(Long id) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nível não encontrado com ID: " + id));
        return mapToResponse(level);
    }

    @Override
    public List<LevelResponse> buscarLevels() {
        return levelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private LevelResponse mapToResponse(Level level) {
        LevelResponse response = new LevelResponse();
        response.setIdLevel(level.getIdLevel());
        response.setTitulo(level.getTitulo());
        response.setDescricao(level.getDescricao());
        response.setDificuldade(level.getDificuldade());
        return response;
    }
}
