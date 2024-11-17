package com.semando.ltda.gateways.responses;

import com.semando.ltda.domains.enums.Ranking;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Classe que representa a resposta com os dados de um usuário.
 * Usada para enviar as informações do usuário nas respostas da API.
 */
@Data
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {
    private Long idUsuario;
    private String nomeUsuario;
    private String email;
    private Ranking ranking;
    private int streak;
    private String bio;
}
