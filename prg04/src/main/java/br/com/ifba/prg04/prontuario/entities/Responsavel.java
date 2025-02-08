package br.com.ifba.prg04.prontuario.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Responsavel {
    private String nome;
    private String contato;

    public Responsavel() {
        this.nome = "Não possui";
        this.contato = "N/A";
    }
}
