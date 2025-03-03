package br.com.ifba.prg04.vacinacao.dto;

import br.com.ifba.prg04.vacinacao.entities.Vacina;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotePostRequestDto {

    @JsonProperty(value = "nome")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String nome;

    @JsonProperty(value = "dataProducao")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private LocalDate dataProducao;

    @JsonProperty(value = "dataVencimento")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private LocalDate dataVencimento;

    @JsonProperty(value = "quantidadeVacinas")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private Integer quantidadeVacinas;

    @JsonProperty(value = "Laboratorio")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private String Laboratorio;

    @JsonProperty(value = "vacina")
    @NotBlank(message = "Este é um campo obrigatorio")
    @NotNull(message = "Este campo nao pode ser nulo")
    private Vacina vacina;
}
