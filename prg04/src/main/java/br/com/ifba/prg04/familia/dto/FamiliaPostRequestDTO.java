package br.com.ifba.prg04.familia.dto;

import br.com.ifba.prg04.familia.entity.Familia;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FamiliaPostRequestDTO {

    @NotBlank(message = "O nome da familia eh obrigatorio")
    private String nome;

    @NotBlank(message = "O endereco eh obrigatorio")
    private String endereco;

    @NotBlank(message = "O responsavel eh obrigatorio")
    private String responsavel;

    @NotEmpty(message = "A lista de membros nao pode ser nula")
    @Valid
    private List<String> membros;
}
