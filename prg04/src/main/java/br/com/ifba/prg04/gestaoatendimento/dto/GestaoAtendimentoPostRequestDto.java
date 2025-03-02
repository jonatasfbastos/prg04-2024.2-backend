package br.com.ifba.prg04.gestaoatendimento.dto;

import java.time.LocalDateTime;

import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GestaoAtendimentoPostRequestDto {
    @NotBlank
    private String codigo;
    @NotNull
    @FutureOrPresent
    private LocalDateTime dataHora;
    @NotBlank
    private String especialidadeMedica;
    private Paciente paciente;

    public GestaoAtendimentoPostRequestDto(){}

}
