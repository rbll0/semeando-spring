package com.semando.ltda.gateways.responses;

import com.semando.ltda.domains.enums.Dificuldade;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


/**
 * Classe que representa a resposta com os dados de um nível.
 * Usada para enviar as informações do nível nas respostas da API.
 */
@Data
public class LevelResponse extends RepresentationModel<LevelResponse> {
    private Long idLevel;
    private String titulo;
    private String descricao;
    private Dificuldade dificuldade;
}
