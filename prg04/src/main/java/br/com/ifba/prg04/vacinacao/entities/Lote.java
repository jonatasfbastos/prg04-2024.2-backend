package br.com.ifba.prg04.vacinacao.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "lotes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Lote extends PersistenceEntity {

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataProducao")
    private LocalDate dataProducao;

    @Column(name = "dataVencimento")
    private LocalDate dataVencimento;

    @Column(name = "quantidadeVacinas")
    private Integer quantidadeVacinas;

    @Column(name = "laboratorio")
    private String laboratorio;

//    @ManyToOne //muitos lotes podem estar associados a uma única vacina.
//    @JoinColumn(name = "vacina_id", nullable = false)
//    private Vacina vacina; // Relacionamento com a vacina
    @ManyToMany(mappedBy = "lotes")
    private Set<Vacina> vacinas;
}
