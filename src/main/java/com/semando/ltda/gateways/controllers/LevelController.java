package com.semando.ltda.gateways.controllers;

import com.semando.ltda.gateways.requests.LevelRequest;
import com.semando.ltda.gateways.responses.LevelResponse;
import com.semando.ltda.usecases.interfaces.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas aos níveis.
 * Fornece endpoints para criar, atualizar, buscar e deletar níveis.
 */
@RestController
@RequestMapping("/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    /**
     * Endpoint para criar um novo nível.
     *
     * @param request dados do nível a ser criado
     * @return resposta contendo o nível criado e o status HTTP 201
     */
    @PostMapping
    public ResponseEntity<LevelResponse> criarLevel(@Valid @RequestBody LevelRequest request) {
        LevelResponse response = levelService.criarLevel(request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar um nível existente.
     *
     * @param id ID do nível a ser atualizado
     * @param request dados atualizados do nível
     * @return resposta contendo o nível atualizado e o status HTTP 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<LevelResponse> atualizarLevel(@PathVariable Long id, @Valid @RequestBody LevelRequest request) {
        LevelResponse response = levelService.atualizarLevel(id, request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar um nível pelo ID.
     *
     * @param id ID do nível
     * @return resposta contendo o nível encontrado e o status HTTP 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<LevelResponse> buscarLevelPorId(@PathVariable Long id) {
        LevelResponse response = levelService.buscarLevelPorId(id);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar todos os níveis.
     *
     * @return lista de níveis e o status HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<LevelResponse>> buscarLevels() {
        List<LevelResponse> response = levelService.buscarLevels();
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar níveis com suporte a paginação.
     *
     * @param pageable parâmetros de paginação
     * @return página de níveis
     */
    @GetMapping("/paginados")
    public ResponseEntity<Page<LevelResponse>> buscarLevelsPaginados(Pageable pageable) {
        Page<LevelResponse> response = levelService.buscarLevelsPaginados(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para deletar um nível pelo ID.
     *
     * @param id ID do nível a ser deletado
     * @return status HTTP 204 (sem conteúdo)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLevel(@PathVariable Long id) {
        levelService.deletarLevel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addHateoasLinks(LevelResponse response) {
        // Link para o próprio recurso do nível
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LevelController.class)
                .buscarLevelPorId(response.getIdLevel())).withSelfRel();

        // Link para a lista de todos os níveis
        Link allLevelsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LevelController.class)
                .buscarLevels()).withRel("all-levels");

        // Link para as perguntas associadas ao nível
        Link perguntasLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerguntaController.class)
                .buscarPerguntasPorLevel(response.getIdLevel())).withRel("level-questions");

        response.add(selfLink);
        response.add(allLevelsLink);
        response.add(perguntasLink);
    }
}
