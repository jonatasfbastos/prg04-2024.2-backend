package br.com.ifba.prg04.termoconsentimento.entity;

import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_termo_consentimento")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class TermoConsentimento extends PersistenceEntity {

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraConsentimento;
    @Column(nullable = false, updatable = false, length = 500)
    private String conteudo;
    @Column(nullable = false, updatable = false)
    private String assinaturaPaciente;
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

}
