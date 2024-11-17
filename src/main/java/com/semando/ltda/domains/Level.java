package com.semando.ltda.domains;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLevel;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificuldade dificuldade;
}
