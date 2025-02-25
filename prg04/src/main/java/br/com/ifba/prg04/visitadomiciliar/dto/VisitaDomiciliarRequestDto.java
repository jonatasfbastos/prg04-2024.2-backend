package br.com.ifba.prg04.visitadomiciliar.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// Classe usada para receber os dados de uma solicitação de criação de visita domiciliar
// garante validações e integridade das informações fornecidas pelo usuário
public class VisitaDomiciliarRequestDto {

    @NotNull(message = "O ID do 'digitadoPor' é obrigatório.")
    @Positive(message = "O ID do 'digitadoPor' deve ser um número positivo.")
    private Long digitadoPorId;

    @NotNull(message = "A data da visita é obrigatória.")
    private LocalDate data;

    @NotNull(message = "O ID do 'conferidoPor' é obrigatório.")
    @Positive(message = "O ID do 'conferidoPor' deve ser um número positivo.")
    private Long conferidoPorId;

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

    @NotBlank(message = "O motivo da visita é obrigatório.")
    private String motivoVisita;

    @NotBlank(message = "O acompanhamento é obrigatório.")
    private String acompanhamento;

    @NotBlank(message = "O controle ambiental é obrigatório.")
    private String controleAmbiental;

    @NotBlank(message = "A antropometria é obrigatória.")
    private String antropometria;

    @NotBlank(message = "Os sinais vitais são obrigatórios.")
    private String sinaisVitais;

    @NotBlank(message = "A glicemia é obrigatória.")
    private String glicemia;

    @NotBlank(message = "O desfecho é obrigatório.")
    private String desfecho;

}

