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

    // Use `insertable = false, updatable = false` aqui para evitar duplicação
    @ManyToOne
    @JoinColumn(name = "id_pergunta", nullable = false, insertable = false, updatable = false)
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_opcao", referencedColumnName = "id_opcao"),
            @JoinColumn(name = "id_pergunta", referencedColumnName = "id_pergunta")
    })
    private Opcao opEscolhida;
}
