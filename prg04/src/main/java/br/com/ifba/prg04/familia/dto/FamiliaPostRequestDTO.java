package br.com.ifba.prg04.familia.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FamiliaPostRequestDTO {

    @NotBlank(message = "O nome da familia eh obrigatorio")
    private String nome;

    @NotBlank(message = "O endereco eh obrigatorio")
    private String endereco;

    @NotNull(message = "O responsavel eh obrigatorio")
    private Long responsavel_id;

    @NotEmpty(message = "A lista de membros nao pode ser nula")
    @Valid
    private List<String> membros;
}
