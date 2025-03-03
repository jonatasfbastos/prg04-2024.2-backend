package br.com.ifba.prg04.vacinacao.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vacinas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Vacina extends PersistenceEntity {

    @Column(name = "nomeCientifico")
    private String nomeCientifico;

    @Column(name = "nomeComum")
    private String nomeComum;

    @Column(name = "nomeLaboratorio")
    private String nomeLaboratorio;

    @Column(name = "idadeFoco")
    private Integer idadeFoco;

    @Column(name = "doencaCombatida")
    private String doencaCombatida;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "metodoAplicacao")
    private String metodoAplicacao;

    @Column(name = "dataVencimento")
    private LocalDate dataVencimento;

    @OneToMany(mappedBy = "vacina")
    private List<Lote> lotes = new ArrayList<>();

}