package com.semando.ltda.gateways.responses;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Classe que representa a resposta com os dados de uma resposta de usuário.
 * Usada para enviar as informações da resposta nas respostas da API.
 */
@Data
public class RespostaResponse extends RepresentationModel<RespostaResponse> {
    private Long idResposta;
    private Long idUsuario;
    private Long idPergunta;
    private Long idOpcaoEscolhida;
}
