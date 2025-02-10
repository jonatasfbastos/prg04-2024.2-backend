package br.com.ifba.prg04.agenda.entity;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Agenda")
public class Agenda extends PersistenceEntity{

    private String titulo;
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;

    private boolean cancelado = false;

    /* Irá se relacionar com usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;*/
}
