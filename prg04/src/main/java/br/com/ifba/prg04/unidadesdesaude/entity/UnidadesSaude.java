package br.com.ifba.prg04.unidadesdesaude.entity;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.unidadesdesaude.enums.Status;
import br.com.ifba.prg04.unidadesdesaude.enums.TiposUnidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidades_saude")
@Inheritance(strategy = InheritanceType.JOINED) // Cria tabelas separadas para cada subclasse
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UnidadesSaude extends PersistenceEntity {
    // Nome da unidade de saúde
    private String nome;

    // Tipo da unidade (Hospital, UPA, UBS, Farmácia)
    @Enumerated(EnumType.STRING)
    private TiposUnidades tipo;

    // Endereço da unidade de saúde (relacionamento 1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "endereco_rua", referencedColumnName = "rua"),
            @JoinColumn(name = "endereco_numero", referencedColumnName = "numero"),
            @JoinColumn(name = "endereco_cep", referencedColumnName = "cep")
    })
    private Endereco endereco;

    // Telefone de contato da unidade
    private String telefone;

    // Horário de funcionamento (ex: "08:00 - 18:00")
    private String horarioFuncionamento;

    // Capacidade máxima de atendimento diário
    private Integer capacidadeAtendimento;

    // Status da unidade (ativa, inativa ou em reforma)
    @Enumerated(EnumType.STRING)
    private Status status;
}

