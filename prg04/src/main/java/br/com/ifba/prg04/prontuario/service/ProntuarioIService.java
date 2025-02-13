package br.com.ifba.prg04.prontuario.service;

import br.com.ifba.prg04.prontuario.dto.ProntuarioGetResponseDto;
import br.com.ifba.prg04.prontuario.dto.ProntuarioPostResponseDto;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProntuarioIService {

    public abstract Prontuario save(String cpf);

    public Page<ProntuarioPostResponseDto> findByIdPaciente(Long id, Pageable pageable);

    public ProntuarioGetResponseDto findById(Long id);
}
