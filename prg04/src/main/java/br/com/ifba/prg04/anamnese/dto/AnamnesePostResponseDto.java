package br.com.ifba.prg04.anamnese.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnamnesePostResponseDto {

    @NotBlank(message = "Queixas não podem estar vazio")
    @NotNull(message = "O campo queixas não podem estar nulo")
    private String queixasPrincipais;

    @NotBlank(message = "Sintomas não podem estar vazio")
    @NotNull(message = "O campo sintomas não podem estar nulo")
    private String sintomasRelatados;

    @NotBlank(message = "Condições atuais não podem estar vazio")
    @NotNull(message = "O campo condições atuais não podem estar nulo")
    private String condicoesAtuais;
}
