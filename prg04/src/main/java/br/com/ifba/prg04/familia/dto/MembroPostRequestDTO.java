package br.com.ifba.prg04.familia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembroPostRequestDTO {

    @NotBlank(message = "O nome do membro eh obrigatorio")
    private String nomeMembro;

    @NotNull(message = "A idade do membro eh obrigatoria")
    private Integer idade;

    @NotBlank(message = "O grau de parentesco eh obrigatorio")
    private String parentescoMembro;
}
