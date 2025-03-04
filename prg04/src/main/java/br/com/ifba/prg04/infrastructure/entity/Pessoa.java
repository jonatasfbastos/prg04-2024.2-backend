package br.com.ifba.prg04.infrastructure.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public abstract class Pessoa {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    @NotBlank
    private String endereco;
    private String estadoCivil;
    private String genero;

}
