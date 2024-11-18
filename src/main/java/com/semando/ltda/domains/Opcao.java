package com.semando.ltda.domains;

import com.semando.ltda.infrastructure.BooleanToYNConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Opcao")
public class Opcao {

    @EmbeddedId
    private OpcaoId id;

    @ManyToOne
    @MapsId("idPergunta")
    @JoinColumn(name = "id_pergunta", nullable = false)
    private Pergunta pergunta;

    @Column(nullable = false)
    private String texto;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "op_correta", nullable = false)
    private Boolean opCorreta;
}
