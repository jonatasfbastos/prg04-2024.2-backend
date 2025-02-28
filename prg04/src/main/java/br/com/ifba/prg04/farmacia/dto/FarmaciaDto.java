package br.com.ifba.prg04.farmacia.dto;

// Importações necessárias para o DTO
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeSaveRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Gera automaticamente getters, setters, toString, equals e hashCode
@Data
// Cria um construtor com todos os campos
@AllArgsConstructor
// Cria um construtor vazio
@NoArgsConstructor
public class FarmaciaDto {
    // Mapeia o campo JSON "unidade" para esta propriedade
    @JsonProperty("unidade")
    // Valida o objeto aninhado de acordo com suas próprias regras de validação
    @Valid
    // Representa os dados da unidade de saúde associados à farmácia
    private UnidadeSaudeSaveRequestDto unidadeSaudeSaveRequestDto;
}