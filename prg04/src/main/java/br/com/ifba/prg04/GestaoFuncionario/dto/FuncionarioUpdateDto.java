package br.com.ifba.prg04.gestaofuncionario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FuncionarioUpdateDto {

    @NotBlank(message = "A senha não pode ser vazia")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "O endereço não pode ser vazio")
    private String endereco;

    @NotBlank(message = "O telefone não pode ser vazio")
    private String telefone;
}
