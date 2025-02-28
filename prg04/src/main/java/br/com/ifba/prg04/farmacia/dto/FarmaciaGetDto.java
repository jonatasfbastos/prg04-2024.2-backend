package br.com.ifba.prg04.farmacia.dto;

import br.com.ifba.prg04.medicamento.entity.Medicamento;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeSaveRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaGetDto {
    @JsonProperty("unidade")
    private UnidadeSaudeGetResponseDto unidadeSaudeGetRequestDto;

    @JsonProperty("medicamentosComuns")
    private List<Medicamento> medicamentosComuns;

    @JsonProperty("medicamentosControlados")
    private List<Medicamento> medicamentosControlados;

    @JsonProperty("medicamentosEspeciais")
    private List<Medicamento> medicamentosEspeciais;
}
