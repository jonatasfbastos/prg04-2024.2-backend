package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequisicaoIService {
    RequisicaoGetResponseDto save(RequisicaoPostRequestDto dto);
    Page<RequisicaoGetResponseDto> findAll(Pageable pageable);
    RequisicaoGetResponseDto findById(Long id);
    Page<RequisicaoGetResponseDto> findByPacienteNome(String nome, Pageable pageable);
    Page<RequisicaoGetResponseDto> findByPacienteCpf(String cpf, Pageable pageable);
    void delete(Long id);
}
