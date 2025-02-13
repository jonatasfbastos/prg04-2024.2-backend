package br.com.ifba.prg04.visitadomiciliar.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitaDomiciliarRequestDto {
    @NotBlank(message = "O campo 'digitadoPor' é obrigatório.")
    private String digitadoPor;

    @NotNull(message = "A data da visita é obrigatória.")
    private LocalDate data;

    @NotBlank(message = "O campo 'conferidoPor' é obrigatório.")
    private String conferidoPor;

    @NotBlank(message = "O número da folha é obrigatório.")
    @Pattern(regexp = "\\d+", message = "O número da folha deve conter apenas números.")
    private String numeroFolha;

    @NotBlank(message = "O CNS do profissional é obrigatório.")
    @Size(min = 15, max = 15, message = "O CNS deve ter exatamente 15 caracteres.")
    private String cns;

    @NotBlank(message = "O CBO do profissional é obrigatório.")
    private String cbo;

    @NotBlank(message = "O CNES do estabelecimento é obrigatório.")
    private String cnes;

    @NotBlank(message = "O INE do profissional é obrigatório.")
    private String ine;
}

