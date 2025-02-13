package br.com.ifba.prg04.documento.dto;

import lombok.Data;

@Data
public class DocumentoPostResponseDto {
    private String nomeArquivo;
    private String tipoArquivo;
    private String urlArquivo;
}
