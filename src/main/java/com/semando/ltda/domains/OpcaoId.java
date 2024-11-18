package com.semando.ltda.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OpcaoId implements Serializable {

    @Column(name = "id_opcao")
    private Integer idOpcao;

    @Column(name = "id_pergunta")
    private Long idPergunta;
}
