package com.semando.ltda.gateways.controllers;

import com.semando.ltda.gateways.requests.OpcaoRequest;
import com.semando.ltda.gateways.responses.OpcaoResponse;
import com.semando.ltda.usecases.interfaces.OpcaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas às opções de resposta.
 * Fornece endpoints para criar, atualizar, buscar e deletar opções de resposta.
 */
@RestController
@RequestMapping("/opcoes")
@RequiredArgsConstructor
public class OpcaoController {

    private final OpcaoService opcaoService;

    // Criar uma nova opção
    @PostMapping
    public ResponseEntity<OpcaoResponse> criarOpcao(@Valid @RequestBody OpcaoRequest request) {
        OpcaoResponse response = opcaoService.criarOpcao(request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Atualizar uma opção existente com idOpcao e idPergunta
    @PutMapping("/{idOpcao}/{idPergunta}")
    public ResponseEntity<OpcaoResponse> atualizarOpcao(
            @PathVariable Integer idOpcao,
            @PathVariable Long idPergunta,
            @Valid @RequestBody OpcaoRequest request) {
        OpcaoResponse response = opcaoService.atualizarOpcao(idOpcao, idPergunta, request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Buscar uma opção específica com idOpcao e idPergunta
    @GetMapping("/{idOpcao}/{idPergunta}")
    public ResponseEntity<OpcaoResponse> buscarOpcaoPorId(
            @PathVariable Integer idOpcao,
            @PathVariable Long idPergunta) {
        OpcaoResponse response = opcaoService.buscarOpcaoPorId(idOpcao, idPergunta);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Buscar todas as opções
    @GetMapping
    public ResponseEntity<List<OpcaoResponse>> buscarOpcoes() {
        List<OpcaoResponse> response = opcaoService.buscarOpcoes();
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Buscar opções por ID da pergunta
    @GetMapping("/pergunta/{perguntaId}")
    public ResponseEntity<List<OpcaoResponse>> buscarOpcoesPorPergunta(@PathVariable Long perguntaId) {
        List<OpcaoResponse> response = opcaoService.buscarOpcoesPorPergunta(perguntaId);
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Deletar uma opção com idOpcao e idPergunta
    @DeleteMapping("/{idOpcao}/{idPergunta}")
    public ResponseEntity<Void> deletarOpcao(
            @PathVariable Integer idOpcao,
            @PathVariable Long idPergunta) {
        opcaoService.deletarOpcao(idOpcao, idPergunta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addHateoasLinks(OpcaoResponse response) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(OpcaoController.class)
                        .buscarOpcaoPorId(response.getIdOpcao(), response.getIdPergunta())).withSelfRel();
        Link allOpcoesLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(OpcaoController.class).buscarOpcoes()).withRel("all-options");
        Link perguntaLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PerguntaController.class)
                        .buscarPerguntaPorId(response.getIdPergunta())).withRel("related-question");

        response.add(selfLink);
        response.add(allOpcoesLink);
        response.add(perguntaLink);
    }
}
