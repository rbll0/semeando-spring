package com.semando.ltda.domains;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Ranking ranking;

    @Column(nullable = false)
    private Integer streak = 0;

    private String bio;
}
