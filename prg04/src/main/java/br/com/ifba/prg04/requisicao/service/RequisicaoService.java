package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.exames.entity.Exame;
import br.com.ifba.prg04.exames.repository.ExameRepository;
import br.com.ifba.prg04.paciente.dto.PacienteGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;
import br.com.ifba.prg04.requisicao.entity.Requisicao;
import br.com.ifba.prg04.requisicao.repository.RequisicaoRepository;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequisicaoService implements RequisicaoIService {

    private final RequisicaoRepository requisicaoRepository;
    private final PacienteRepository pacienteRepository;
    private final ExameRepository exameRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository,
                             PacienteRepository pacienteRepository,
                             ExameRepository exameRepository) {
        this.requisicaoRepository = requisicaoRepository;
        this.pacienteRepository = pacienteRepository;
        this.exameRepository = exameRepository;
    }

    @Override
    @Transactional
    public RequisicaoGetResponseDto save(RequisicaoPostRequestDto dto) {
        // Busca o paciente pelo CPF, lança exceção se não encontrado
        Paciente paciente = pacienteRepository.findByCpf(dto.getCpfPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com CPF " + dto.getCpfPaciente() + " não encontrado!"));

        Requisicao requisicao = new Requisicao();
        requisicao.setDataRequisicao(dto.getDataRequisicao());
        requisicao.setPaciente(paciente);

        // Buscar os objetos Exame a partir dos IDs enviados no DTO
        if (dto.getExameIds() != null && !dto.getExameIds().isEmpty()) {
            List<Exame> exames = exameRepository.findAllById(dto.getExameIds());
            if (exames.size() != dto.getExameIds().size()) {
                throw new EntityNotFoundException("Um ou mais exames não foram encontrados!");
            }
            requisicao.setExames(exames);
        } else {
            requisicao.setExames(Collections.emptyList());
        }

        return toDto(requisicaoRepository.save(requisicao));
    }

    @Override
    public Page<RequisicaoGetResponseDto> findAll(Pageable pageable) {
        return requisicaoRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public RequisicaoGetResponseDto findById(Long id) {
        return requisicaoRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Requisição com ID " + id + " não encontrada!"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!requisicaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Requisição com ID " + id + " não encontrada!");
        }
        requisicaoRepository.deleteById(id);
    }

    @Override
    public Page<RequisicaoGetResponseDto> findByPacienteNome(String nome, Pageable pageable) {
        return requisicaoRepository.findByPacienteNome(nome, pageable)
                .map(this::toDto);
    }

    @Override
    public Page<RequisicaoGetResponseDto> findByPacienteCpf(String cpf, Pageable pageable) {
        return requisicaoRepository.findByPacienteCpf(cpf, pageable)
                .map(this::toDto);
    }

    private RequisicaoGetResponseDto toDto(Requisicao requisicao) {
        // Verifica se o paciente existe antes de criar o DTO
        PacienteGetResponseDto pacienteDto = null;
        if (requisicao.getPaciente() != null) {
            pacienteDto = new PacienteGetResponseDto(
                    requisicao.getPaciente().getId(),
                    requisicao.getPaciente().getNome(),
                    requisicao.getPaciente().getCpf(),
                    requisicao.getPaciente().getDataNascimento(),
                    requisicao.getPaciente().getGenero(),
                    requisicao.getPaciente().getEstadoCivil(),
                    requisicao.getPaciente().getEndereco(),
                    requisicao.getPaciente().getTelefone(),
                    requisicao.getPaciente().getEmail(),
                    requisicao.getPaciente().getResponsavel()
            );
        }

        // Converter List<Exame> para List<String> com as descrições dos exames
        List<String> examesList = (requisicao.getExames() == null || requisicao.getExames().isEmpty())
                ? Collections.emptyList()
                : requisicao.getExames().stream()
                .map(Exame::getDescricao)
                .collect(Collectors.toList());

        return new RequisicaoGetResponseDto(
                requisicao.getId(),
                requisicao.getDataRequisicao(),
                pacienteDto,
                examesList
        );
    }
}