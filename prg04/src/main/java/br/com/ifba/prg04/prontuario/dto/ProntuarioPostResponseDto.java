package br.com.ifba.prg04.prontuario.dto;

import br.com.ifba.prg04.anamnese.dto.AnamneseGetResponseDto;
import br.com.ifba.prg04.anamnese.dto.AnamnesePostResponseDto;
import br.com.ifba.prg04.documento.dto.DocumentoDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProntuarioPostResponseDto {

    private Long id;

    private LocalDate dataCriacao;

    //private String codigoProntuario;

    private List<AnamneseGetResponseDto> anamneses;

    private List<DocumentoDto> documentos;
}
