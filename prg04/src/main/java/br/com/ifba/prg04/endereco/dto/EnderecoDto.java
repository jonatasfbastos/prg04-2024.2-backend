package br.com.ifba.prg04.endereco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
    @JsonProperty("id")
    @Valid
    private EnderecoIdDto id;

    @JsonProperty("complemento")
    @Size(max = 255, message = "O complemento deve ter no máximo 255 caracteres.")
    private String complemento;

    @JsonProperty("bairro")
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 255, message = "O bairro deve ter no máximo 255 caracteres.")
    private String bairro;

    @JsonProperty("cidade")
    @NotBlank(message = "A cidade não pode estar vazia.")
    @Size(max = 255, message = "A cidade deve ter no máximo 255 caracteres.")
    private String cidade;

    @JsonProperty("uf")
    @NotBlank(message = "A UF não pode estar vazia.")
    @Pattern(regexp = "[A-Z]{2}", message = "A UF deve ser um estado brasileiro com 2 letras maiúsculas.")
    private String uf;
}
