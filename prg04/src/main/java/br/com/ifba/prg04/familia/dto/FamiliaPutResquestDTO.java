package br.com.ifba.prg04.familia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class FamiliaPutResquestDTO {
    @NotBlank(message = "O nome da familia é obrigatorio.")
    private String nome;

    @NotBlank(message = "O endereco é obrigatorio.")
    private String endereco;

    //@NotNull(message = "A lista de membros nao pode ser nula.")
    @Size(min = 0)
    private List<String> membros;

    @NotNull(message = "O ID do responsável eh obrigatorio.")
    private Long responsavelId;
}
