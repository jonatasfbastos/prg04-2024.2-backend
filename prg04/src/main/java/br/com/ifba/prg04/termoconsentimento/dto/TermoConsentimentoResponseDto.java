package br.com.ifba.prg04.termoconsentimento.dto;

import br.com.ifba.prg04.funcionario.dto.FuncionarioSimpleResponseDto;
import br.com.ifba.prg04.paciente.dto.PacienteSimpleResponseDto;
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
    private PacienteSimpleResponseDto paciente;
    private LocalDateTime dataHoraConsentimento;
    private String conteudo;
    private String assinaturaPaciente;
    private FuncionarioSimpleResponseDto funcionario;

}
