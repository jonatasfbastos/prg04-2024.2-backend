package br.com.ifba.prg04.termoconsentimento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class TermoConsentimentoCreateDto {

    @NotBlank(message = "O CPF do paciente não pode ser vazio")
    private String cpfPaciente;
    @NotBlank(message = "O conteúdo do termo não pode ser vazio")
    @Size(max = 500, message = "O conteúdo do termo não pode ter mais de 500 caracteres")
    private String conteudo;
    @NotBlank(message = "A assinatura do paciente não pode ser vazia")
    private String assinaturaPaciente;
    @NotBlank(message = "O CPF do funcionário não pode ser vazio")
    private String codigoFuncionario;

}
