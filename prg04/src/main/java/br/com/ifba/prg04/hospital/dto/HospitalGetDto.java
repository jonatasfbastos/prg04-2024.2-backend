package br.com.ifba.prg04.hospital.dto;

// Importações necessárias para o DTO
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
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
public class HospitalGetDto {
    // Mapeia o campo JSON "unidade" para informações básicas da unidade de saúde
    @JsonProperty("unidade")
    private UnidadeSaudeGetResponseDto unidadeSaude;

    // Representa a quantidade de leitos disponíveis na UTI
    @JsonProperty("leitosUTI")
    private Integer leitosUTI;

    // Representa a quantidade de leitos disponíveis na enfermaria
    @JsonProperty("leitosEnfermaria")
    private Integer leitosEnfermaria;

    // Representa a quantidade de leitos privativos disponíveis
    @JsonProperty("leitosPrivativo")
    private Integer leitosPrivativo;

    // Representa a quantidade de leitos destinados à emergência
    @JsonProperty("leitosEmergencia")
    private Integer leitosEmergencia;

    // Lista de especialidades médicas atendidas pelo hospital
    @JsonProperty("especialidadesAtendidas")
    private List<String> especialidadesAtendidas;

    // Lista de serviços laboratoriais oferecidos pelo hospital
    @JsonProperty("servicosLaboratoriais")
    private List<String> servicosLaboratoriais;

    // Lista de exames de diagnóstico por imagem disponíveis
    @JsonProperty("examesDiagnosticoImagem")
    private List<String> examesDiagnosticoImagem;

    // Lista de centros cirúrgicos disponíveis no hospital
    @JsonProperty("centrosCirurgicos")
    private List<String> centrosCirurgicos;
}