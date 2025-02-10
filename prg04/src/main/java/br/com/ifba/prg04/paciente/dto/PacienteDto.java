package br.com.ifba.prg04.paciente.dto;

import br.com.ifba.prg04.prontuario.dto.ProntuarioDto;

import java.time.LocalDate;
import java.util.List;

public class PacienteDto {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String genero;
    private List<ProntuarioDto> prontuarios;
}
