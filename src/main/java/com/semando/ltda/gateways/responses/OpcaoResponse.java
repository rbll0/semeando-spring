package com.semando.ltda.gateways.responses;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Classe que representa a resposta com os dados de uma opção de resposta.
 * Usada para enviar as informações da opção nas respostas da API.
 */
@Data
public class OpcaoResponse extends RepresentationModel<OpcaoResponse> {
    private Long idOpcao;
    private Long idPergunta;
    private String texto;
    private boolean correta;
}
