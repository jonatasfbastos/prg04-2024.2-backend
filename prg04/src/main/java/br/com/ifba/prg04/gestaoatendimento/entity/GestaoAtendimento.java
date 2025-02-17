package br.com.ifba.prg04.gestaoatendimento.entity;

import java.time.LocalDateTime;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.usuario.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Gestao_de_Atendimentos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "dataHora"})// não permitir que o usuario marque na mesma data e horario
    })
@AllArgsConstructor
public class GestaoAtendimento extends PersistenceEntity {
    @Column
    private String code;
    private LocalDateTime dataHora;
    private String especialidadeMedica;
    @ManyToOne // relacao com usuario muitos para um
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public GestaoAtendimento(){}
}
