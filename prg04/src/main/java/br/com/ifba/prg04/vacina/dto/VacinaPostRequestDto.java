package br.com.ifba.prg04.vacina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacinaPostRequestDto {

    @JsonProperty(value = "nomeCientifico")
    private String nomeCientifico;

    @JsonProperty(value = "nomeComum")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String nomeComum;

    @JsonProperty(value = "nomeLaboratorio")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String nomeLaboratorio;

    @JsonProperty(value = "idadeFoco")
    private Integer idadeFoco;

    @JsonProperty(value = "doencaCombatida")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String doencaCombatida;

    @JsonProperty(value = "observacao")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String observacao;

    @JsonProperty(value = "metodoAplicacao")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String metodoAplicacao;

    @JsonProperty(value = "dataVencimento")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private LocalDate dataVencimento;

    @JsonProperty(value = "lotes")
    private List<Long> loteIds;
}
