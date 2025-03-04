package br.com.ifba.prg04.gestaoatendimento.entity;

import java.time.LocalDateTime;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.paciente.entity.Paciente;
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
    private String codigo;
    private LocalDateTime dataHora;
    private String especialidadeMedica;
    @ManyToOne    
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    public GestaoAtendimento(){}
}
