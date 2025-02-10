package br.com.ifba.prg04.termoconsentimento.dto.mapper;

import br.com.ifba.prg04.termoconsentimento.dto.TermoConsentimentoCreateDto;
import br.com.ifba.prg04.termoconsentimento.dto.TermoConsentimentoResponseDto;
import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TermoConsentimentoMapper {

    public static TermoConsentimento toEntity(TermoConsentimentoCreateDto dto) {
        return new ModelMapper().map(dto, TermoConsentimento.class);
    }

    public static TermoConsentimentoResponseDto toDto(TermoConsentimento termoConsentimento) {
        return new ModelMapper().map(termoConsentimento, TermoConsentimentoResponseDto.class);
    }
    
}
