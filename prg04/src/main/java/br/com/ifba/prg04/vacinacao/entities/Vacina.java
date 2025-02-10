package br.com.ifba.prg04.vacinacao.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vacinas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Vacina extends PersistenceEntity {

    private String nomeCientifico;
    private String nomeComum;
    private String nomeLaboratorio;
    private Integer idadeFoco;
    private Integer quantidade;
    private String doencaCombatida;
    private String observacao;
    private String metodoAplicacao;
    private LocalDate dataVencimento;

    @OneToMany(mappedBy = "vacina")
    private List<Lote> loteAtrelado; // Lista de lotes associados à vacina

}