package br.com.ifba.prg04.paciente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Responsavel {
    @Column(name = "Nome_Responsavel", nullable = true)
    private String nomeResponsavel;
    @Column(name = "Contato_Responsavel", nullable = true)
    private String contatoResponsavel;

}
