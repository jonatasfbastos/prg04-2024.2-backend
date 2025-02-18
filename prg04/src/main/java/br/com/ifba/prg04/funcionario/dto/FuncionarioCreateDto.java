package br.com.ifba.prg04.funcionario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FuncionarioCreateDto {
    @NotBlank
    private String codigo;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotBlank
    private String categoria;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String endereco;

    @NotBlank
    private String telefone;
}