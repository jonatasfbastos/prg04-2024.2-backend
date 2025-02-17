package br.com.ifba.prg04.visitadomiciliar.service;

import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarRequestDto;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VisitaDomiciliarIService {
    VisitaDomiciliarResponseDto salvar(VisitaDomiciliarRequestDto dto);
    List<VisitaDomiciliarResponseDto> listarTodas();
    Page<VisitaDomiciliarResponseDto> listarTodas(Pageable pageable);
    VisitaDomiciliarResponseDto buscarPorId(Long id);
    VisitaDomiciliarResponseDto atualizar(Long id, VisitaDomiciliarRequestDto dto);
    void deletar(Long id);
}




