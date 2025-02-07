package br.com.ifba.prg04.vacinacao.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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
    private String loteAtrelado;
    private String doencaCombatida;
    private String observacao;
    private String metodoAplicacao;
    private LocalDate dataVencimento;

}