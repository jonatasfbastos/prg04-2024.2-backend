package br.com.ifba.prg04.campanha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CampanhaPostRequestDto {

    @JsonProperty("nomec")
    @NotBlank(message = "O nome da campanha é obrigatório")
    private String nomec;

    @JsonProperty("vacina")
    @NotBlank(message = "A Vacina é obrigatória")
    private String vacina;

    @JsonProperty("publicoAlvo")
    @NotBlank(message = "O público alvo é obrigatório")
    private String publicoAlvo;

    @JsonProperty("dataInicio")
    @NotBlank(message = "A data de início é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "A data de início deve estar no formato yyyy-MM-dd")
    private String dataInicio;

    @JsonProperty("dataFim")
    @NotBlank(message = "A data de fim é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "A data de fim deve estar no formato yyyy-MM-dd")
    private String dataFim;
}
