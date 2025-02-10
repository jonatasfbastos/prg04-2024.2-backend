package br.com.ifba.prg04.paciente.entity;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Pacientes")
public class Paciente extends PersistenceEntity {

    @Column(name = "nome", insertable = false, updatable = false)
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String genero;
    private String estadoCivil;
    private String endereco;
    private String telefone;
    private String email;

    @Embedded
    private Responsavel responsavel;
}
