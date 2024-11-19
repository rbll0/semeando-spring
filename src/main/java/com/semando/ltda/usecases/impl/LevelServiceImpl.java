package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Level;
import com.semando.ltda.gateways.procedures.LevelProcedures;
import com.semando.ltda.gateways.repositories.LevelRepository;
import com.semando.ltda.gateways.requests.LevelRequest;
import com.semando.ltda.gateways.responses.LevelResponse;
import com.semando.ltda.usecases.interfaces.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final LevelProcedures levelProcedures;

    @Override
    public LevelResponse criarLevel(LevelRequest request) {
        // Chamada à procedure para inserir level
        levelProcedures.inserirLevel(
                request.getTitulo(),
                request.getDescricao(),
                request.getDificuldade().name()
        );

        // Buscar o level recém-criado para retornar no response
        Level level = levelRepository.findTopByTituloOrderByIdLevelDesc(request.getTitulo())
                .orElseThrow(() -> new NoSuchElementException("Nível não encontrado após inserção."));

        return mapToResponse(level);
    }

    @Override
    public LevelResponse atualizarLevel(Long id, LevelRequest request) {
        // Chamada à procedure para atualizar level
        levelProcedures.atualizarLevel(
                id,
                request.getTitulo(),
                request.getDescricao(),
                request.getDificuldade().name()
        );

        // Buscar o level atualizado para retornar no response
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nível não encontrado com ID: " + id));

        return mapToResponse(level);
    }

    @Override
    public void deletarLevel(Long id) {
        // Chamada à procedure para deletar level
        levelProcedures.deletarLevel(id);

        // Deletar o level no repositório para consistência
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

    @Override
    public Page<LevelResponse> buscarLevelsPaginados(Pageable pageable) {
        return levelRepository.findAll(pageable)
                .map(this::mapToResponse);
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
