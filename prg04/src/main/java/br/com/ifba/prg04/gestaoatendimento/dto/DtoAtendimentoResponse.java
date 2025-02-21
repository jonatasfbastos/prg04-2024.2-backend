package br.com.ifba.prg04.gestaoatendimento.dto;

import java.time.LocalDateTime;

import br.com.ifba.prg04.usuario.dto.UsuarioResponseDto;
import br.com.ifba.prg04.usuario.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoAtendimentoResponse {
    private String code;
    private LocalDateTime dataHora;
    private String especialidadeMedica;
    private UsuarioResponseDto usuarioDto;

    public DtoAtendimentoResponse(){}
}
