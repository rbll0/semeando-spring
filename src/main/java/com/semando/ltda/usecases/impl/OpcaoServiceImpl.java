package com.semando.ltda.usecases.impl;

import com.semando.ltda.domains.Opcao;
import com.semando.ltda.domains.OpcaoId;
import com.semando.ltda.domains.Pergunta;
import com.semando.ltda.gateways.procedures.OpcaoProcedures;
import com.semando.ltda.gateways.repositories.OpcaoRepository;
import com.semando.ltda.gateways.repositories.PerguntaRepository;
import com.semando.ltda.gateways.requests.OpcaoRequest;
import com.semando.ltda.gateways.responses.OpcaoResponse;
import com.semando.ltda.usecases.interfaces.OpcaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Opção. Contém a lógica
 * para criar, atualizar, buscar e deletar opções.
 */
@Service
@RequiredArgsConstructor
public class OpcaoServiceImpl implements OpcaoService {

    private final OpcaoRepository opcaoRepository;
    private final OpcaoProcedures opcaoProcedures;

    @Override
    public OpcaoResponse criarOpcao(OpcaoRequest request) {
        // Converte o campo correta (Boolean) para char ('S' ou 'N')
        char corretaChar = request.getCorreta() ? 'S' : 'N';

        // Chama a procedure para inserir a opção
        opcaoProcedures.inserirOpcao(
                request.getIdPergunta(),
                request.getIdOpcao(),
                request.getTexto(),
                corretaChar
        );

        // Busca a opção recém-criada
        OpcaoId opcaoId = new OpcaoId(request.getIdOpcao(), request.getIdPergunta());
        Opcao opcao = opcaoRepository.findById(opcaoId)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada após inserção com ID: " + opcaoId));

        return mapToResponse(opcao);
    }

    @Override
    public OpcaoResponse atualizarOpcao(Integer idOpcao, Long idPergunta, OpcaoRequest request) {
        // Converte o campo correta (Boolean) para char ('S' ou 'N')
        char corretaChar = request.getCorreta() ? 'S' : 'N';

        // Chama a procedure para atualizar a opção
        opcaoProcedures.atualizarOpcao(
                idPergunta,
                idOpcao,
                request.getTexto(),
                corretaChar
        );

        // Busca a opção atualizada
        OpcaoId opcaoId = new OpcaoId(idOpcao, idPergunta);
        Opcao opcao = opcaoRepository.findById(opcaoId)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + opcaoId));

        return mapToResponse(opcao);
    }


    @Override
    public OpcaoResponse buscarOpcaoPorId(Integer idOpcao, Long idPergunta) {
        OpcaoId opcaoId = new OpcaoId(idOpcao, idPergunta);

        Opcao opcao = opcaoRepository.findById(opcaoId)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada com ID: " + opcaoId));

        return mapToResponse(opcao);
    }

    @Override
    public List<OpcaoResponse> buscarOpcoes() {
        return opcaoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OpcaoResponse> buscarOpcoesPorPergunta(Long perguntaId) {
        return opcaoRepository.findByPergunta_IdPergunta(perguntaId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarOpcao(Integer idOpcao, Long idPergunta) {
        // Chama a procedure para deletar a opção
        opcaoProcedures.deletarOpcao(idPergunta, idOpcao);

        // Remove do repositório para manter consistência
        OpcaoId opcaoId = new OpcaoId(idOpcao, idPergunta);
        opcaoRepository.deleteById(opcaoId);
    }

    private OpcaoResponse mapToResponse(Opcao opcao) {
        OpcaoResponse response = new OpcaoResponse();
        response.setIdOpcao(opcao.getId().getIdOpcao());
        response.setIdPergunta(opcao.getId().getIdPergunta());
        response.setTexto(opcao.getTexto());
        response.setCorreta(opcao.getOpCorreta());
        return response;
    }
}
