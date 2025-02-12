package br.com.ifba.prg04.visitadomiciliar.service;

import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarRequestDto;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarResponseDto;
import br.com.ifba.prg04.visitadomiciliar.entity.VisitaDomiciliar;
import br.com.ifba.prg04.visitadomiciliar.repository.VisitaDomiciliarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitaDomiciliarService implements VisitaDomiciliarIService{
    private final VisitaDomiciliarRepository repository;
    private final ModelMapper modelMapper;

    public VisitaDomiciliarService(VisitaDomiciliarRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VisitaDomiciliarResponseDto salvar(VisitaDomiciliarRequestDto dto) {
        VisitaDomiciliar visita = modelMapper.map(dto, VisitaDomiciliar.class);
        visita = repository.save(visita);
        return modelMapper.map(visita, VisitaDomiciliarResponseDto.class);
    }

    @Override
    public List<VisitaDomiciliarResponseDto> listarTodas() {
        List<VisitaDomiciliar> visitas = repository.findAll();
        return visitas.stream()
                .map(visita -> modelMapper.map(visita, VisitaDomiciliarResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VisitaDomiciliarResponseDto buscarPorId(Long id) {
        VisitaDomiciliar visita = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita não encontrada."));
        return modelMapper.map(visita, VisitaDomiciliarResponseDto.class);
    }

    @Override
    public VisitaDomiciliarResponseDto atualizar(Long id, VisitaDomiciliarRequestDto dto) {
        VisitaDomiciliar visita = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita não encontrada."));

        // Atualiza os campos da visita
        modelMapper.map(dto, visita);
        visita = repository.save(visita);

        return modelMapper.map(visita, VisitaDomiciliarResponseDto.class);
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Visita não encontrada.");
        }
        repository.deleteById(id);
    }
}
