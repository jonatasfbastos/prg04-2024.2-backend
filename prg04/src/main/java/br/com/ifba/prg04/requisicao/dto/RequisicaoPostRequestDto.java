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
    private String nomePaciente;
    private String cpfPaciente;
    private List<String> exames;
    private LocalDateTime dataRequisicao;  // Pode ser nula
}
