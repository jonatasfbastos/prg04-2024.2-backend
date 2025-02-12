package br.com.ifba.prg04.paciente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteGetResponseDto {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String genero;
}
