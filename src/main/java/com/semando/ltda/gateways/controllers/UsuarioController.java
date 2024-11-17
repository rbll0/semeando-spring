package com.semando.ltda.gateways.controllers;


import com.semando.ltda.gateways.requests.UsuarioRequest;
import com.semando.ltda.gateways.responses.UsuarioResponse;
import com.semando.ltda.usecases.interfaces.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas a usuários.
 * Fornece endpoints para criar, atualizar, buscar e deletar usuários.
 */
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Endpoint para criar um novo usuário.
     *
     * @param request dados do usuário a ser criado
     * @return resposta contendo o usuário criado e o status HTTP 201
     */
    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.criarUsuario(request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar um usuário existente.
     *
     * @param id ID do usuário a ser atualizado
     * @param request dados atualizados do usuário
     * @return resposta contendo o usuário atualizado e o status HTTP 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.atualizarUsuario(id, request);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar um usuário pelo ID.
     *
     * @param id ID do usuário
     * @return resposta contendo o usuário encontrado e o status HTTP 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponse response = usuarioService.buscarUsuarioPorId(id);
        addHateoasLinks(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar todos os usuários.
     *
     * @return lista de usuários e o status HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscarUsuarios() {
        List<UsuarioResponse> response = usuarioService.buscarUsuarios();
        response.forEach(this::addHateoasLinks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para deletar um usuário pelo ID.
     *
     * @param id ID do usuário a ser deletado
     * @return status HTTP 204 (sem conteúdo)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addHateoasLinks(UsuarioResponse response) {
        // Link para o próprio recurso do usuário
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuarioPorId(response.getIdUsuario())).withSelfRel();

        // Link para a lista de todos os usuários
        Link allUsuariosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuarios()).withRel("all-usuarios");

        // Link para ver todos os níveis disponíveis
        Link levelsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LevelController.class)
                .buscarLevels()).withRel("all-levels");

        // Link para acessar o progresso do usuário em quizzes
        Link progressoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RespostaController.class)
                .buscarRespostasPorUsuario(response.getIdUsuario())).withRel("user-progress");

        response.add(selfLink);
        response.add(allUsuariosLink);
        response.add(levelsLink);
        response.add(progressoLink);
    }
}
