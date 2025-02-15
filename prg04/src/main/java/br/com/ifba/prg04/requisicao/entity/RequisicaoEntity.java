package br.com.ifba.prg04.requisicao.entity;

import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "O paciente é obrigatório")
    private Paciente paciente;

    @ElementCollection
    @CollectionTable(
            name = "requisicao_exames", // Nome da tabela que armazenará os exames
            joinColumns = @JoinColumn(name = "requisicao_id") // Coluna que referencia a requisição
    )
    @Column(name = "exame") // Nome da coluna que armazenará cada exame
    @NotNull(message = "A lista de exames não pode ser nula")
    private List<String> exames;
}