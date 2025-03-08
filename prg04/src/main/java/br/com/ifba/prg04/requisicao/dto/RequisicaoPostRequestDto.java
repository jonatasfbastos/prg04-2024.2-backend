package br.com.ifba.prg04.requisicao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoPostRequestDto {
    @NotNull(message = "O nome do paciente é obrigatório")
    private String nomePaciente;

    @NotNull(message = "O CPF do paciente é obrigatório")
    private String cpfPaciente;

    @NotNull(message = "A lista de exames não pode ser nula")
    private List<Long> exameIds; // Ajustado de List<String> para List<Long>, representando IDs de Exame

    private LocalDateTime dataRequisicao; // Pode ser nula
}