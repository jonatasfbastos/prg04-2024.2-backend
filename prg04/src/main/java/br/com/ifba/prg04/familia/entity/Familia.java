package br.com.ifba.prg04.familia.entity;

import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Familia extends PersistenceEntity implements Serializable {

    private String nome;
    private String endereco;

    @ElementCollection //mapeia uma colecao de tipos basicos como string ou integer
    @CollectionTable(name = "familia_membros", joinColumns = @JoinColumn(name = "familia_id"))
    @Column(name = "membro") //coluna que armazena os valores
    private List<String> membros;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Funcionario responsavel;

}
