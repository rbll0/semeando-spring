package com.semando.ltda.domains;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Opcao")
@IdClass(OpcaoId.class) // Usando chave composta
public class Opcao {

    @Id
    @Column(name = "id_opcao")
    private Integer idOpcao;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pergunta", nullable = false)
    private Pergunta pergunta;

    @Column(nullable = false)
    private String texto;

    @Column(name = "op_correta", nullable = false)
    private Boolean opCorreta = false;
}
