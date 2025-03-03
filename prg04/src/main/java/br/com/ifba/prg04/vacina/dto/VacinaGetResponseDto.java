package br.com.ifba.prg04.vacina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacinaGetResponseDto {
    @JsonProperty(value = "nomeCientifico")
    private String nomeCientifico;

    @JsonProperty(value = "nomeComum")
    private String nomeComum;

    @JsonProperty(value = "nomeLaboratorio")
    private String nomeLaboratorio;

    @JsonProperty(value = "idadeFoco")
    private Integer idadeFoco;

    @JsonProperty(value = "doencaCombatida")
    private String doencaCombatida;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "metodoAplicacao")
    private String metodoAplicacao;

    @JsonProperty(value = "dataVencimento")
    private LocalDate dataVencimento;
}
