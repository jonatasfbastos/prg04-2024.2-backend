package br.com.ifba.prg04.familia.entity;

import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.entity.Responsavel;
import br.com.ifba.prg04.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String endereco;
    @ManyToMany
    @JoinTable(
        name = "familia_paciente",
        joinColumns = @JoinColumn(name = "familia_id"),
        inverseJoinColumns = @JoinColumn(name = "paciente_id")
    )
    private List<Paciente> membros;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Funcionario responsavel;

}
