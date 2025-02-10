package br.com.ifba.prg04.anamnese.dto;

import lombok.Data;

@Data
public class AnamneseResponseDto {
    private String queixasPrincipais;
    private String sintomasRelatados;
    private String condicoesAtuais;
}
