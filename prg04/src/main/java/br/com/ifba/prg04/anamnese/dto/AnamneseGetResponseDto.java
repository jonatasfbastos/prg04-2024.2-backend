package br.com.ifba.prg04.anamnese.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnamneseGetResponseDto {

    private Long id;

    private String queixasPrincipais;

    private String sintomasRelatados;

    private String condicoesAtuais;
}
