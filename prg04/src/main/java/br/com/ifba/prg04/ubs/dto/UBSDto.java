package br.com.ifba.prg04.ubs.dto;

import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeSaveRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UBSDto {
    // Mapeia o campo JSON "unidade" para esta propriedade
    @JsonProperty("unidade")
    // Valida o objeto aninhado de acordo com suas próprias regras de validação
    @Valid
    // Representa os dados da unidade de saúde associados à UBS
    private UnidadeSaudeSaveRequestDto unidadeSaudeSaveRequestDto;
}
