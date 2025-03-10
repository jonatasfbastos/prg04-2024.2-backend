package br.com.ifba.prg04.familia.entity;

import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Familia extends PersistenceEntity implements Serializable {

    private String nome;
    private String endereco;

    @OneToMany
    @JoinColumn(name = "membros_id") //coluna que armazena os valores
    private List<Paciente> membros;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Funcionario responsavel;

}
