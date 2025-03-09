package br.com.ifba.prg04.campanha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CampanhaPostRequestDto {

    @JsonProperty("nome")
    @NotBlank(message = "O nome da campanha é obrigatório")
    private String nome;

    @JsonProperty("vacina_id")
    @NotNull(message = "O ID da vacina é obrigatório")
    private Long vacinaId;

    @JsonProperty("publico_alvo")
    @NotBlank(message = "O público alvo é obrigatório")
    private String publicoAlvo;

    @JsonProperty("data_inicio")
    @NotBlank(message = "A data de início é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "A data de início deve estar no formato yyyy-MM-dd")
    private String dataInicio;

    @JsonProperty("data_fim")
    @NotBlank(message = "A data de fim é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "A data de fim deve estar no formato yyyy-MM-dd")
    private String dataFim;
}
