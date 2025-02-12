package br.com.ifba.prg04.paciente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacientePutResponseDto {

    @NotBlank(message = "O nome não pode estar vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

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
