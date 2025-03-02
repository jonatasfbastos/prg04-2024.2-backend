package br.com.ifba.prg04.gestaoatendimento.dto;

import java.time.LocalDateTime;

import br.com.ifba.prg04.paciente.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GestaoAtendimentoGetResponseDto {
    private String code;
    private LocalDateTime dataHora;
    private String especialidadeMedica;
    private Paciente paciente;

    public GestaoAtendimentoGetResponseDto(){}
}
