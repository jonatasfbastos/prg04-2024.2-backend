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

    @NotNull(message = "A data da requisição não pode ser nula")
    private LocalDateTime dataRequisicao;

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "A lista de exames não pode ser nula")
    private List<String> exames; // Agora é uma lista de strings
}