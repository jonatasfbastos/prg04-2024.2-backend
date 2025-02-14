package br.com.ifba.prg04.prontuario.dto;

import br.com.ifba.prg04.anamnese.dto.AnamneseGetResponseDto;
import br.com.ifba.prg04.documento.dto.DocumentoGetResponseDto;
import br.com.ifba.prg04.documento.dto.DocumentoPostResponseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProntuarioGetResponseDto {

    private Long id;

    private LocalDate dataCriacao;

    private List<AnamneseGetResponseDto> anamneses;

    private List<DocumentoGetResponseDto> documentos;
}
