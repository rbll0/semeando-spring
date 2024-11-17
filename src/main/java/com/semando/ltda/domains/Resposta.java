package com.semando.ltda.domains;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Resposta")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResposta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_pergunta", nullable = false)
    private Pergunta pergunta;

    @Column(name = "op_escolhida", nullable = false)
    private Integer opEscolhida;
}
