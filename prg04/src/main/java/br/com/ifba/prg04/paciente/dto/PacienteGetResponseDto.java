package br.com.ifba.prg04.paciente.dto;

import br.com.ifba.prg04.paciente.entity.Responsavel;
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
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String genero;
    private String estadoCivil;
    private String endereco;
    private String telefone;
    private String email;

    private Responsavel responsavel;
}
