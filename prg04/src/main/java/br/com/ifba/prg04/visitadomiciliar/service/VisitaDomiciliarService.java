package br.com.ifba.prg04.visitadomiciliar.service;

import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.funcionario.repositories.FuncionarioRepository;
import br.com.ifba.prg04.infrastructure.exception.DatabaseException;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.infrastructure.exception.UniqueViolationException;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarRequestDto;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarResponseDto;
import br.com.ifba.prg04.visitadomiciliar.entity.VisitaDomiciliar;
import br.com.ifba.prg04.visitadomiciliar.repository.VisitaDomiciliarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitaDomiciliarService implements VisitaDomiciliarIService {

    private final VisitaDomiciliarRepository repository;
    private final FuncionarioRepository funcionarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ObjectMapperUtil objectMapperUtil;

    @Override
    @Transactional
    public VisitaDomiciliarResponseDto salvar(VisitaDomiciliarRequestDto dto) {
        try {
            // Busca os funcionários e paciente pelo ID
            Funcionario digitadoPor = funcionarioRepository.findById(dto.getDigitadoPorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + dto.getDigitadoPorId()));
            Funcionario conferidoPor = funcionarioRepository.findById(dto.getConferidoPorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + dto.getConferidoPorId()));
            Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + dto.getPacienteId()));

            // Converte DTO para entidade
            VisitaDomiciliar visita = objectMapperUtil.map(dto, VisitaDomiciliar.class);
            visita.setDigitadoPor(digitadoPor);
            visita.setConferidoPor(conferidoPor);
            visita.setPaciente(paciente);

            // Salva no banco de dados
            visita = repository.save(visita);

            // Converte entidade para DTO de resposta
            VisitaDomiciliarResponseDto responseDto = objectMapperUtil.map(visita, VisitaDomiciliarResponseDto.class);
            responseDto.setDigitadoPor(digitadoPor.getNome());
            responseDto.setConferidoPor(conferidoPor.getNome());
            responseDto.setPacienteNome(paciente.getNome());

            return responseDto;
        } catch (DataIntegrityViolationException e) {
            throw new UniqueViolationException("Já existe uma visita domiciliar com os mesmos dados cadastrados.");
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar visita domiciliar: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitaDomiciliarResponseDto> listarTodas() {
        List<VisitaDomiciliar> visitas = repository.findAll();
        return objectMapperUtil.mapAll(visitas, VisitaDomiciliarResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VisitaDomiciliarResponseDto> listarTodas(Pageable pageable) {
        // Retorna lista paginada de visitas domiciliares
        Page<VisitaDomiciliar> page = repository.findAll(pageable);
        return page.map(visita -> {
            VisitaDomiciliarResponseDto dto = objectMapperUtil.map(visita, VisitaDomiciliarResponseDto.class);
            dto.setPacienteNome(visita.getPaciente().getNome());
            return dto;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public VisitaDomiciliarResponseDto buscarPorId(Long id) {
        // Busca uma visita domiciliar pelo ID
        VisitaDomiciliar visita = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visita domiciliar não encontrada com o ID: " + id));
        VisitaDomiciliarResponseDto responseDto = objectMapperUtil.map(visita, VisitaDomiciliarResponseDto.class);
        responseDto.setPacienteNome(visita.getPaciente().getNome());
        return responseDto;
    }

    @Override
    @Transactional
    public VisitaDomiciliarResponseDto atualizar(Long id, VisitaDomiciliarRequestDto dto) {
        // Busca a visita pelo ID
        VisitaDomiciliar visita = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visita domiciliar não encontrada com o ID: " + id));

        // Busca os funcionários e paciente
        Funcionario digitadoPor = funcionarioRepository.findById(dto.getDigitadoPorId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + dto.getDigitadoPorId()));
        Funcionario conferidoPor = funcionarioRepository.findById(dto.getConferidoPorId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + dto.getConferidoPorId()));
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + dto.getPacienteId()));

        // Atualiza os dados da visita
        visita.setDigitadoPor(digitadoPor);
        visita.setData(dto.getData());
        visita.setConferidoPor(conferidoPor);
        visita.setNumeroFolha(dto.getNumeroFolha());
        visita.setCns(dto.getCns());
        visita.setCbo(dto.getCbo());
        visita.setCnes(dto.getCnes());
        visita.setIne(dto.getIne());
        visita.setMotivoVisita(dto.getMotivoVisita());
        visita.setAcompanhamento(dto.getAcompanhamento());
        visita.setControleAmbiental(dto.getControleAmbiental());
        visita.setAntropometria(dto.getAntropometria());
        visita.setSinaisVitais(dto.getSinaisVitais());
        visita.setGlicemia(dto.getGlicemia());
        visita.setDesfecho(dto.getDesfecho());
        visita.setPaciente(paciente);

        // Salva a visita atualizada
        try {
            visita = repository.save(visita);
            VisitaDomiciliarResponseDto responseDto = objectMapperUtil.map(visita, VisitaDomiciliarResponseDto.class);
            responseDto.setDigitadoPor(digitadoPor.getNome());
            responseDto.setConferidoPor(conferidoPor.getNome());
            responseDto.setPacienteNome(paciente.getNome());
            return responseDto;
        } catch (DataIntegrityViolationException e) {
            throw new UniqueViolationException("Já existe uma visita domiciliar com os mesmos dados cadastrados.");
        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar visita domiciliar com ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        // Verifica se a visita existe antes de deletar
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Visita domiciliar não encontrada com o ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao deletar visita domiciliar com ID " + id + ": " + e.getMessage());
        }
    }
}

