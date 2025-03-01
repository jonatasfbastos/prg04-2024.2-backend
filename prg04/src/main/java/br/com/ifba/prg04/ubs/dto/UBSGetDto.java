package br.com.ifba.prg04.ubs.dto;

// Importações necessárias para o DTO
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
public class UBSGetDto {
    // Mapeia o campo JSON "unidade" para informações básicas da unidade de saúde
    @JsonProperty("unidade")
    private UnidadeSaudeGetResponseDto unidadeSaudeGetResponseDto;

    // Representa a quantidade de consultórios disponíveis na UBS
    @JsonProperty("quantidadeConsultorios")
    private Integer quantidadeConsultorios;

    // Lista de programas de saúde oferecidos pela UBS
    @JsonProperty("programasSaude")
    private List<String> programasSaude;
}