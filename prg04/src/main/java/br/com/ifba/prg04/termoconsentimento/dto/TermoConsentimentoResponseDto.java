package br.com.ifba.prg04.termoconsentimento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TermoConsentimentoResponseDto {

    private Long id;
    //private String paciente;
    private LocalDateTime dataHoraConsentimento;
    private String conteudo;
    private String assinaturaPaciente;
    //private String funcionario;

}
