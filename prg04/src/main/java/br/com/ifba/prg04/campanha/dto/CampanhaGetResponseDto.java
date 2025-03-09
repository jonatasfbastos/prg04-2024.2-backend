package br.com.ifba.prg04.campanha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaGetResponseDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("vacina_id")
    private Long vacinaId;

    @JsonProperty("publico_alvo")
    private String publicoAlvo;

    @JsonProperty("data_inicio")
    private String dataInicio;

    @JsonProperty("data_fim")
    private String dataFim;

    public void setVacinaNome(String nomeComum) {
    }
}


