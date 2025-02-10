package br.com.ifba.prg04.usuario.dto.mapper;

import br.com.ifba.prg04.usuario.dto.UsuarioCreateDto;
import br.com.ifba.prg04.usuario.dto.UsuarioResponseDto;
import br.com.ifba.prg04.usuario.dto.UsuarioUpdateDto;
import br.com.ifba.prg04.usuario.entity.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioMapper {

    public static Usuario toEntity(UsuarioCreateDto dto) {
        return new ModelMapper().map(dto, Usuario.class);
    }

    public static Usuario toEntity(UsuarioUpdateDto dto) {
        return new ModelMapper().map(dto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioResponseDto.class);
    }
}
