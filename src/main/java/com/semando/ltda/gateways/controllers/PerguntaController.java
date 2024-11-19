package com.semando.ltda.gateways.controllers;

import com.semando.ltda.gateways.requests.PerguntaRequest;
import com.semando.ltda.gateways.responses.PerguntaResponse;
import com.semando.ltda.usecases.interfaces.PerguntaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas às perguntas.
 * Fornece endpoints para criar, atualizar, buscar e deletar perguntas.
 */
@RestController
@RequestMapping("/perguntas")
@RequiredArgsConstructor
public class PerguntaController {

    private final PerguntaService perguntaService;

    /**
     * Endpoint para criar uma nova pergunta.
     *
     * @param request dados da pergunta a ser criada
     * @return resposta contendo a pergunta criada e o status HTTP 201
     */
    @PostMapping
    public ResponseEntity<PerguntaResponse> criarPergunta(@Valid @RequestBody PerguntaRequest request) {
        PerguntaResponse response = perguntaService.criarPergunta(request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar uma pergunta existente.
     *
     * @param id ID da pergunta a ser atualizada
     * @param request dados atualizados da pergunta
     * @return resposta contendo a pergunta atualizada e o status HTTP 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<PerguntaResponse> atualizarPergunta(@PathVariable Long id, @Valid @RequestBody PerguntaRequest request) {
        PerguntaResponse response = perguntaService.atualizarPergunta(id, request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar uma pergunta pelo ID.
     *
     * @param id ID da pergunta
     * @return resposta contendo a pergunta encontrada e o status HTTP 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<PerguntaResponse> buscarPerguntaPorId(@PathVariable Long id) {
        PerguntaResponse response = perguntaService.buscarPerguntaPorId(id);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar todas as perguntas.
     *
     * @return lista de perguntas e o status HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<PerguntaResponse>> buscarPerguntas() {
        List<PerguntaResponse> response = perguntaService.buscarPerguntas();
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/paginadas")
    public ResponseEntity<Page<PerguntaResponse>> buscarPerguntasPaginadas(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<PerguntaResponse> response = perguntaService.buscarPerguntasPaginadas(pageable);
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar perguntas associadas a um nível específico.
     *
     * @param levelId ID do nível
     * @return lista de perguntas do nível e o status HTTP 200
     */
    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<PerguntaResponse>> buscarPerguntasPorLevel(@PathVariable Long levelId) {
        List<PerguntaResponse> response = perguntaService.buscarPerguntasPorLevel(levelId);
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/level/{levelId}/paginadas")
    public ResponseEntity<Page<PerguntaResponse>> buscarPerguntasPorLevelPaginadas(
            @PathVariable Long levelId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<PerguntaResponse> response = perguntaService.buscarPerguntasPorLevelPaginadas(levelId, pageable);
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para deletar uma pergunta pelo ID.
     *
     * @param id ID da pergunta a ser deletada
     * @return status HTTP 204 (sem conteúdo)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPergunta(@PathVariable Long id) {
        perguntaService.deletarPergunta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addHateoasLinks(PerguntaResponse response) {
        // Link para o próprio recurso da pergunta
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerguntaController.class)
                .buscarPerguntaPorId(response.getIdPergunta())).withSelfRel();

        // Link para a lista de todas as perguntas
        Link allPerguntasLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerguntaController.class)
                .buscarPerguntas()).withRel("all-perguntas");

        // Link para as opções de resposta associadas à pergunta
        Link opcoesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OpcaoController.class)
                .buscarOpcoesPorPergunta(response.getIdPergunta())).withRel("question-options");

        response.add(selfLink);
        response.add(allPerguntasLink);
        response.add(opcoesLink);
    }
}
