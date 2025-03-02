package br.com.ifba.prg04.vacinacao.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "vacinas_retroativas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CarteiraVacinaRetroativa extends PersistenceEntity {
    @Column(name = "nomeComum")
    private String nomeComum;
    @Column(name = "dataAplicacao")
    private LocalDate dataAplicacao;
    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    private Paciente paciente;
}
