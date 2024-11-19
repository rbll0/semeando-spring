package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.LevelRequest;
import com.semando.ltda.gateways.responses.LevelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface que define os serviços relacionados aos níveis.
 * Contém os métodos para criar, atualizar, buscar e deletar níveis.
 */
@Service
public interface LevelService {
    LevelResponse criarLevel(LevelRequest request);

    LevelResponse atualizarLevel(Long id, LevelRequest request);

    LevelResponse buscarLevelPorId(Long id);

    List<LevelResponse> buscarLevels();

    Page<LevelResponse> buscarLevelsPaginados(Pageable pageable);

    void deletarLevel(Long id);

}
