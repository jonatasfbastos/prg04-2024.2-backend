package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;

import java.util.List;

public interface RequisicaoIService {
    RequisicaoGetResponseDto save(RequisicaoPostRequestDto dto);
    List<RequisicaoGetResponseDto> findAll();
    RequisicaoGetResponseDto findById(Long id);
    List<RequisicaoGetResponseDto> findByPacienteNome(String nome);
    List<RequisicaoGetResponseDto> findByPacienteCpf(String cpf);
    void delete(Long id);
}