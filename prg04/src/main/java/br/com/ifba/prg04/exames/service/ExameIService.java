package br.com.ifba.prg04.exames.service;

import br.com.ifba.prg04.exames.dto.ExameGetResponseDto;
import br.com.ifba.prg04.exames.dto.ExamePostRequestDto;
import java.util.List;

public interface ExameIService {
    ExameGetResponseDto saveExame(ExamePostRequestDto exameDto);
    void deleteExame(Long id);
    List<ExameGetResponseDto> getAllExames();
    ExameGetResponseDto getExameById(Long id);
    List<ExameGetResponseDto> getExameByDescricao(String descricao);
}