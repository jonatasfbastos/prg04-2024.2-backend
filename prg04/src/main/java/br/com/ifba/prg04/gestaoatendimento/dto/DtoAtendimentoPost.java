package br.com.ifba.prg04.gestaoatendimento.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoAtendimentoPost {
    @NotBlank
    private String codigo;
    @NotNull
    @FutureOrPresent
    private LocalDateTime dataHora;
    @NotBlank
    private String especialidadeMedica;
    private String Usuario;

    public DtoAtendimentoPost(){}

}
