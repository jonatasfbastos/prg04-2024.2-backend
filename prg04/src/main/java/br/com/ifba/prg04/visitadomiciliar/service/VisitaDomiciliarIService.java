package br.com.ifba.prg04.visitadomiciliar.service;

import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarRequestDto;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarResponseDto;

import java.util.List;
public interface VisitaDomiciliarIService {
    VisitaDomiciliarResponseDto salvar(VisitaDomiciliarRequestDto dto);
    List<VisitaDomiciliarResponseDto> listarTodas();
    VisitaDomiciliarResponseDto buscarPorId(Long id);
    VisitaDomiciliarResponseDto atualizar(Long id, VisitaDomiciliarRequestDto dto);
    void deletar(Long id);
}
