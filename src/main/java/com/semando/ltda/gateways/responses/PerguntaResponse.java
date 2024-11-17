package com.semando.ltda.gateways.responses;

import com.semando.ltda.domains.enums.TipoPergunta;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Classe que representa a resposta com os dados de uma pergunta.
 * Usada para enviar as informações da pergunta nas respostas da API.
 */
@Data
public class PerguntaResponse extends RepresentationModel<PerguntaResponse> {

    private Long idPergunta;
    private Long idLevel;
    private String texto;
    private TipoPergunta tipoPergunta;
}
