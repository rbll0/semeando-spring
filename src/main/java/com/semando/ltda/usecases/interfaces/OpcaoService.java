package com.semando.ltda.usecases.interfaces;

import com.semando.ltda.gateways.requests.OpcaoRequest;
import com.semando.ltda.gateways.responses.OpcaoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface que define os serviços relacionados às opções de resposta.
 * Contém os métodos para criar, atualizar, buscar e deletar opções.
 */
@Service
public interface OpcaoService {
    /**
     * Cria uma nova opção.
     *
     * @param request dados da opção a ser criada
     * @return a opção criada como resposta
     */
    OpcaoResponse criarOpcao(OpcaoRequest request);

    /**
     * Atualiza uma opção existente com os dados fornecidos.
     *
     * @param idOpcao ID da opção dentro da pergunta
     * @param idPergunta ID da pergunta associada
     * @param request dados atualizados da opção
     * @return a opção atualizada como resposta
     */
    OpcaoResponse atualizarOpcao(Integer idOpcao, Long idPergunta, OpcaoRequest request);

    /**
     * Busca uma opção por seu ID composto.
     *
     * @param idOpcao ID da opção dentro da pergunta
     * @param idPergunta ID da pergunta associada
     * @return a opção encontrada como resposta
     */
    OpcaoResponse buscarOpcaoPorId(Integer idOpcao, Long idPergunta);

    /**
     * Busca todas as opções cadastradas.
     *
     * @return lista de todas as opções como resposta
     */
    List<OpcaoResponse> buscarOpcoes();

    /**
     * Busca todas as opções associadas a uma pergunta específica.
     *
     * @param idPergunta ID da pergunta associada
     * @return lista de opções para a pergunta especificada
     */
    List<OpcaoResponse> buscarOpcoesPorPergunta(Long idPergunta);

    /**
     * Deleta uma opção por seu ID composto.
     *
     * @param idOpcao ID da opção dentro da pergunta
     * @param idPergunta ID da pergunta associada
     */
    void deletarOpcao(Integer idOpcao, Long idPergunta);
}
