package br.com.ifba.prg04.exames.service;

import br.com.ifba.prg04.exames.dto.ExameGetResponseDto;
import br.com.ifba.prg04.exames.dto.ExamePostRequestDto;
import br.com.ifba.prg04.exames.entity.Exame;
import br.com.ifba.prg04.exames.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExameService implements ExameIService {

    @Autowired
    private ExameRepository exameRepository;

    @Override
    public ExameGetResponseDto saveExame(ExamePostRequestDto exameDto) {
        Exame exame = new Exame();
        exame.setDescricao(exameDto.getDescricao());
        Exame savedExame = exameRepository.save(exame);
        return mapToDto(savedExame);
    }

    @Override
    public void deleteExame(Long id) {
        exameRepository.deleteById(id);
    }

    @Override
    public List<ExameGetResponseDto> getAllExames() {
        return exameRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExameGetResponseDto getExameById(Long id) {
        Exame exame = exameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado"));
        return mapToDto(exame);
    }

    @Override
    public List<ExameGetResponseDto> getExameByDescricao(String descricao) {
        return exameRepository.findByDescricao(descricao).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ExameGetResponseDto mapToDto(Exame exame) {
        ExameGetResponseDto dto = new ExameGetResponseDto();
        dto.setId(exame.getId());
        dto.setDescricao(exame.getDescricao());
        return dto;
    }
}