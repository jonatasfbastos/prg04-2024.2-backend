package br.com.ifba.prg04.paciente.dto;

import br.com.ifba.prg04.prontuario.dto.ProntuarioDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacientePostResponseDto {

    @NotBlank(message = "O nome não pode estar vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "O CPF não pode estar vazio")
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "O gênero não pode estar vazio")
    private String genero;

    @Size(max = 50, message = "O estado civil não pode ter mais de 50 caracteres")
    private String estadoCivil;

    @Size(max = 150, message = "O endereço não pode ter mais de 150 caracteres")
    private String endereco;

    @NotBlank(message = "O telefone não pode estar vazio")
    //@Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Formato de telefone inválido")
    private String telefone;

    @Email(message = "O email deve ser válido")
    private String email;
}
