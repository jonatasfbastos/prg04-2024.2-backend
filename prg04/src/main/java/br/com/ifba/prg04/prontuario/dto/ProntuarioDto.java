package br.com.ifba.prg04.prontuario.dto;

import br.com.ifba.prg04.anamnese.dto.AnamneseResponseDto;
import br.com.ifba.prg04.documento.dto.DocumentoDto;

import java.time.LocalDateTime;
import java.util.List;

public class ProntuarioDto {
    private LocalDateTime dataCriacao;
    private List<AnamneseResponseDto> anamneses;
    private List<DocumentoDto> documentos;
}
