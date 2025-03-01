package br.com.ifba.prg04.farmacia.dto;

// Importações necessárias para o DTO
import br.com.ifba.prg04.medicamento.entity.Medicamento;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Gera automaticamente getters, setters, toString, equals e hashCode
@Data
// Cria um construtor com todos os campos
@AllArgsConstructor
// Cria um construtor vazio
@NoArgsConstructor
public class FarmaciaGetDto {
    // Mapeia o campo JSON "unidade" para informações básicas da unidade de saúde
    @JsonProperty("unidade")
    private UnidadeSaudeGetResponseDto unidadeSaudeGetRequestDto;

    // Lista de medicamentos comuns disponíveis na farmácia
    @JsonProperty("medicamentosComuns")
    private List<Medicamento> medicamentosComuns;

    // Lista de medicamentos controlados disponíveis na farmácia
    @JsonProperty("medicamentosControlados")
    private List<Medicamento> medicamentosControlados;

    // Lista de medicamentos especiais disponíveis na farmácia
    @JsonProperty("medicamentosEspeciais")
    private List<Medicamento> medicamentosEspeciais;
}