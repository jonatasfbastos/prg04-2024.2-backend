package br.com.ifba.prg04.requisicao.entity;

import br.com.ifba.prg04.exames.entity.Exame; // Novo pacote
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
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dataRequisicao;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "O paciente é obrigatório")
    private Paciente paciente;

    @ManyToMany
    @JoinTable(
            name = "requisicao_exame",
            joinColumns = @JoinColumn(name = "requisicao_id"),
            inverseJoinColumns = @JoinColumn(name = "exame_id")
    )
    @NotNull(message = "A lista de exames não pode ser nula")
    private List<Exame> exames;
}