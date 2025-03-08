package br.com.ifba.prg04.requisicao.entity;

import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicoes_exames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dataRequisicao;

    // Ajuste para enviar apenas o nome do paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "O paciente é obrigatório")
    private Paciente paciente;

    // Armazenar exames como uma string separada por vírgulas
    @Column(name = "exames", nullable = false)
    @NotNull(message = "A lista de exames não pode ser nula")
    private String exames;  // Exemplo: "exame1, exame2, exame3"
}
