package br.com.ifba.prg04.campanha.entity;

import br.com.ifba.prg04.vacina.entity.Vacina;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="campanha")
@Getter
@Setter
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="campanha_id")
    private Long id;

    @Column(name="nome", length = 100)
    @NotNull
    private String nome;

    @NotNull
    @Column(name="publico_alvo", length = 100)
    private String publicoAlvo;

    @NotNull
    @Column(name="data_inicio", length = 45)
    private String dataInicio;

    @NotNull
    @Column(name="data_fim", length = 45)
    private String dataFim;

    @NotNull
    @Column(name="vacina_id")
    private Long vacinaId;

}
