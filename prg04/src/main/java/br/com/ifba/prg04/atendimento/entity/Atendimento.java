package br.com.ifba.prg04.atendimento.entity;

import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    private String tipoAtendimento;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraTermino;
}
