package br.com.ifba.prg04.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String email;
    private String nivelAcesso;
}
