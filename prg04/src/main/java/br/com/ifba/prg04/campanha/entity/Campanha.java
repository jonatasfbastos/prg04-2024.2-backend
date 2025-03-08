package br.com.ifba.prg04.campanha.entity;

import br.com.ifba.prg04.vacina.entity.Vacina;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="campanha")
@Getter
@Setter
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome", length = 100)
    @NotNull
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "campanha_vacina", // Nome da tabela associativa
            joinColumns = @JoinColumn(name = "campanha_id"), // Coluna de referência para Campanha
            inverseJoinColumns = @JoinColumn(name = "vacina_id") // Coluna de referência para Vacina
    )
    private List<Vacina> vacinas = new ArrayList<>();
;

    @NotNull
    @Column(name="publico_alvo", length = 100)
    private String publicoAlvo;

    @NotNull
    @Column(name="data_inicio", length = 45)
    private String dataInicio;

    @NotNull
    @Column(name="data_fim", length = 45)
    private String dataFim;

}
