package br.com.ifba.prg04.atendimento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AtendimentoDTO {
    private Long id;
    private String tipoAtendimento;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraTermino;
    private Long pacienteId;
    private String pacienteNome;
}
