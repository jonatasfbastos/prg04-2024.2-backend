package br.com.ifba.prg04.prontuario.dto.mapper;

import br.com.ifba.prg04.prontuario.dto.ProntuarioGetResponseDto;
import br.com.ifba.prg04.prontuario.dto.ProntuarioPostResponseDto;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProntuarioMapper {

    public static Prontuario toEntity(ProntuarioPostResponseDto dto) {
        return new ModelMapper().map(dto, Prontuario.class);
    }

    public static ProntuarioPostResponseDto toDto(Prontuario prontuario) {
        return new ModelMapper().map(prontuario, ProntuarioPostResponseDto.class);
    }

    public static ProntuarioGetResponseDto toGetDto(Prontuario prontuario) {
        return new ModelMapper().map(prontuario, ProntuarioGetResponseDto.class);
    }

}
