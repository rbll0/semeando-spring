package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.*;
import com.semando.ltda.gateways.repositories.OpcaoRepository;
import com.semando.ltda.gateways.repositories.PerguntaRepository;
import com.semando.ltda.gateways.repositories.RespostaRepository;
import com.semando.ltda.gateways.repositories.UsuarioRepository;
import com.semando.ltda.gateways.requests.RespostaRequest;
import com.semando.ltda.gateways.responses.RespostaResponse;
import com.semando.ltda.usecases.interfaces.RespostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespostaServiceImpl implements RespostaService {

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerguntaRepository perguntaRepository;
    private final OpcaoRepository opcaoRepository;

    @Override
    public RespostaResponse criarResposta(RespostaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + request.getIdUsuario()));
        Pergunta pergunta = perguntaRepository.findById(request.getIdPergunta())
                .orElseThrow(() -> new NoSuchElementException("Pergunta não encontrada com ID: " + request.getIdPergunta()));
        Opcao opcaoEscolhida = opcaoRepository.findById(new OpcaoId(request.getIdPergunta(), request.getIdOpcaoEscolhida()))
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + request.getIdOpcaoEscolhida()));

        Resposta resposta = new Resposta();
        resposta.setUsuario(usuario);
        resposta.setPergunta(pergunta);
        resposta.setOpcaoEscolhida(opcaoEscolhida);
        resposta = respostaRepository.save(resposta);
        return mapToResponse(resposta);
    }

    @Override
    public RespostaResponse atualizarResposta(Long id, RespostaRequest request) {
        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resposta não encontrada com ID: " + id));
        resposta.setIdOpcaoEscolhida(request.getIdOpcaoEscolhida());
        resposta = respostaRepository.save(resposta);
        return mapToResponse(resposta);
    }

    @Override
    public RespostaResponse buscarRespostaPorId(Long id) {
        Resposta resposta = respostaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resposta não encontrada com ID: " + id));
        return mapToResponse(resposta);
    }

    @Override
    public List<RespostaResponse> buscarRespostas() {
        return respostaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RespostaResponse> buscarRespostasPorUsuario(Long usuarioId) {
        return respostaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarResposta(Long id) {
        respostaRepository.deleteById(id);
    }

    private RespostaResponse mapToResponse(Resposta resposta) {
        RespostaResponse response = new RespostaResponse();
        response.setIdResposta(resposta.getIdResposta());
        response.setIdUsuario(resposta.getIdUsuario());
        response.setIdPergunta(resposta.getIdPergunta());
        response.setIdOpcaoEscolhida(resposta.getIdOpcaoEscolhida());
        return response;
    }
}
