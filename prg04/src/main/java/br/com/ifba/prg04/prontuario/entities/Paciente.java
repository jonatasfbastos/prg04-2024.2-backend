package br.com.ifba.prg04.prontuario.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

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
