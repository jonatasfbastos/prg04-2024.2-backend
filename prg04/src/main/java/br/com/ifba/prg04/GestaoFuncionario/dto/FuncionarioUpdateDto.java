package br.com.ifba.prg04.GestaoFuncionario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FuncionarioUpdateDto {
    @NotBlank
    private String senha;

    @NotBlank
    private String endereco;

    @NotBlank
    private String telefone;
}