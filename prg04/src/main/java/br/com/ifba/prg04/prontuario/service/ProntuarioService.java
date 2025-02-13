package br.com.ifba.prg04.prontuario.service;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import br.com.ifba.prg04.prontuario.dto.ProntuarioGetResponseDto;
import br.com.ifba.prg04.prontuario.dto.ProntuarioPostResponseDto;
import br.com.ifba.prg04.prontuario.dto.mapper.ProntuarioMapper;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import br.com.ifba.prg04.prontuario.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProntuarioService implements ProntuarioIService{

    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    @Override
    public Prontuario save(String cpf) {
        LocalDate dataAtual = LocalDate.now();  // Data atual

        // Recupera o paciente pelo CPF
        Optional<Paciente> paciente = pacienteRepository.findByCpf(cpf);

        try {
            // Se o paciente não for encontrado, lança uma BusinessException
            Paciente pacienteEncontrado = paciente.orElseThrow(() ->
                    new BusinessException("Paciente não encontrado para o CPF: " + cpf)
            );

            // Cria o prontuário e define os dados
            Prontuario prontuario = new Prontuario();
            prontuario.setPaciente(pacienteEncontrado);
            prontuario.setDataCriacao(dataAtual);

            // Salva o prontuário e retorna
            return prontuarioRepository.save(prontuario);
        } catch (BusinessException e) {
            // Lança uma exceção mais específica e com detalhes adicionais
            throw new BusinessException("Erro ao tentar salvar os dados para o CPF: " + cpf, e);
        } catch (Exception e) {
            // Captura outras exceções e lança uma nova BusinessException
            throw new BusinessException("Erro inesperado ao tentar salvar o prontuário", e);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ProntuarioPostResponseDto> findByIdPaciente(Long id, Pageable pageable) {
        log.info("Buscando paciente com ID: {}", id);
        return prontuarioRepository.findByIdPaciente(id, pageable)
                .map(ProntuarioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProntuarioGetResponseDto findById(Long id) {
        log.info("Buscando prontuário com ID: {}", id);

        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Prontuário não encontrado"));

        return ProntuarioMapper.toGetDto(prontuario);
    }




}
