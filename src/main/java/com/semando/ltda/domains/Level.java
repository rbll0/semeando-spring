package com.semando.ltda.domains;

import com.semando.ltda.domains.enums.Dificuldade;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_level")
    private Long idLevel;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificuldade dificuldade;
}
