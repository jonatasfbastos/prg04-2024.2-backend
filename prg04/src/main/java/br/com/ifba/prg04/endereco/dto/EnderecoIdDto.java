package br.com.ifba.prg04.endereco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoIdDto {
    @JsonProperty("rua")
    @NotBlank(message = "A rua do endereço não pode estar vazia.")
    private String rua;

    @JsonProperty("numero")
    @NotBlank(message = "O número do endereço não pode estar vazio.")
    //@Pattern(regexp = "\\d+", message = "O número deve conter apenas dígitos.")
    private String numero;

    @JsonProperty("cep")
    @NotBlank(message = "O CEP do endereço não pode estar vazio.")
    //@Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 'XXXXX-XXX'.")
    private String cep;
}
