package br.com.ifba.prg04.requisicao.entity;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "requisicoes_exames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data da requisição não pode ser nula")
    private LocalDateTime dataRequisicao;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    @NotNull(message = "O médico é obrigatório")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "O paciente é obrigatório")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "tipo_exame_id", nullable = false)
    @NotNull(message = "O tipo de exame é obrigatório")
    private TipoExame tipoExame;
}
