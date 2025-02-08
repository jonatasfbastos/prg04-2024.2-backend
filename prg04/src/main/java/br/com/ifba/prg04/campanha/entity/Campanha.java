package br.com.ifba.prg04.campanha.entity;

import br.com.ifba.prg04.vacinacao.entities.Vacina;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String vacina;
    private String publicoAlvo;
    private String dataInicio;
    private String dataFim;

}
