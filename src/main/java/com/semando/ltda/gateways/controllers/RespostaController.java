package com.semando.ltda.gateways.controllers;

import com.semando.ltda.gateways.requests.RespostaRequest;
import com.semando.ltda.gateways.responses.RespostaResponse;
import com.semando.ltda.usecases.interfaces.RespostaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas às respostas dos usuários.
 * Fornece endpoints para criar, atualizar, buscar e deletar respostas.
 */
@RestController
@RequestMapping("/respostas")
@RequiredArgsConstructor
public class RespostaController {

    private final RespostaService respostaService;

    /**
     * Endpoint para criar uma nova resposta.
     *
     * @param request dados da resposta a ser criada
     * @return resposta contendo a resposta criada e o status HTTP 201
     */
    @PostMapping
    public ResponseEntity<RespostaResponse> criarResposta(@Valid @RequestBody RespostaRequest request) {
        RespostaResponse response = respostaService.criarResposta(request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar uma resposta existente.
     *
     * @param id ID da resposta a ser atualizada
     * @param request dados atualizados da resposta
     * @return resposta contendo a resposta atualizada e o status HTTP 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<RespostaResponse> atualizarResposta(@PathVariable Long id, @Valid @RequestBody RespostaRequest request) {
        RespostaResponse response = respostaService.atualizarResposta(id, request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar uma resposta pelo ID.
     *
     * @param id ID da resposta
     * @return resposta contendo a resposta encontrada e o status HTTP 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespostaResponse> buscarRespostaPorId(@PathVariable Long id) {
        RespostaResponse response = respostaService.buscarRespostaPorId(id);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar todas as respostas.
     *
     * @return lista de respostas e o status HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<RespostaResponse>> buscarRespostas() {
        List<RespostaResponse> response = respostaService.buscarRespostas();
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar respostas de um usuário específico.
     *
     * @param usuarioId ID do usuário
     * @return lista de respostas do usuário e o status HTTP 200
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RespostaResponse>> buscarRespostasPorUsuario(@PathVariable Long usuarioId) {
        List<RespostaResponse> response = respostaService.buscarRespostasPorUsuario(usuarioId);
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para deletar uma resposta pelo ID.
     *
     * @param id ID da resposta a ser deletada
     * @return status HTTP 204 (sem conteúdo)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addHateoasLinks(RespostaResponse response) {
        // Link para o próprio recurso da resposta
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RespostaController.class)
                .buscarRespostaPorId(response.getIdResposta())).withSelfRel();

        // Link para a lista de todas as respostas
        Link allRespostasLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RespostaController.class)
                .buscarRespostas()).withRel("all-respostas");

        // Link para a pergunta associada à resposta
        Link perguntaLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerguntaController.class)
                .buscarPerguntaPorId(response.getIdPergunta())).withRel("related-question");

        // Link para o usuário associado à resposta
        Link usuarioLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuarioPorId(response.getIdUsuario())).withRel("related-user");

        response.add(selfLink);
        response.add(allRespostasLink);
        response.add(perguntaLink);
        response.add(usuarioLink);
    }
}
