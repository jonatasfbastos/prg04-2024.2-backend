package br.com.ifba.prg04.gestaofuncionario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FuncionarioResponseDto {
    private Long id;
    private String codigo;
    private String login;
    private String categoria;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
}