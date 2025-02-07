package br.com.ifba.prg04.prontuario.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Anamnese extends PersistenceEntity {

    @ManyToOne
    @JoinColumn(name = "prontuario_id", nullable = false)
    private Prontuario prontuario;

    private String queixasPrincipais;
    private String sintomasRelatados;
    private String condicoesAtuais;
}
