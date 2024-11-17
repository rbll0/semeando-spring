package com.semando.ltda.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OpcaoId implements Serializable {

    @Column(name = "id_opcao")
    private Integer idOpcao;

    @Column(name = "id_pergunta")
    private Long pergunta; // Representa o id da Pergunta
}
