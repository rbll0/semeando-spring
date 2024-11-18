package com.semando.ltda.domains;

import com.semando.ltda.domains.enums.TipoPergunta;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_Pergunta")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pergunta")
    private Long idPergunta;

    @ManyToOne
    @JoinColumn(name = "id_level", nullable = false)
    private Level level;

    @Column(nullable = false)
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pergunta", nullable = false)
    private TipoPergunta tipoPergunta;
}
