package br.com.ifba.prg04.vacinacao.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "lotes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Lote extends PersistenceEntity {
    private String nome;
    private LocalDate dataProducao;
    private LocalDate dataVencimento;
    private Integer quantidadeVacinas;
    private String Laboratorio;

    @ManyToOne //muitos lotes podem estar associados a uma única vacina.
    @JoinColumn(name = "vacina_id", nullable = false)
    private Vacina vacina; // Relacionamento com a vacina

}
