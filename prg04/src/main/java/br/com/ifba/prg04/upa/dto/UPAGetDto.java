package br.com.ifba.prg04.upa.dto;

// Importações necessárias para o DTO
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import br.com.ifba.prg04.upa.enums.PorteUPA;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Gera automaticamente getters, setters, toString, equals e hashCode
@Data
// Cria um construtor com todos os campos
@AllArgsConstructor
// Cria um construtor vazio
@NoArgsConstructor
public class UPAGetDto {
    // Mapeia o campo JSON "unidade" para informações básicas da unidade de saúde
    @JsonProperty("unidade")
    private UnidadeSaudeGetResponseDto unidadeSaudeGetRequestDto;

    // Representa o porte da UPA (Unidade de Pronto Atendimento), definido por um enum
    @JsonProperty("porte")
    private PorteUPA porte;
}
