package br.com.ifba.prg04.gestaoatendimento.entity;

import java.time.LocalDateTime;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.usuario.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Gestao_de_Atendimentos")
@AllArgsConstructor
public class GestaoAtendimento extends PersistenceEntity {
    @Column(nullable = false, unique=true)
    private String code;
    private LocalDateTime dataHora;
    private String especialidadeMedica;
    @ManyToOne // relacao com usuario muitos para um
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public GestaoAtendimento(){}
}
