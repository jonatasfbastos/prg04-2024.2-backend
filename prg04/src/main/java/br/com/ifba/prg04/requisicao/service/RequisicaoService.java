package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.exames.entity.Exame; // Novo import para a entidade Exame
import br.com.ifba.prg04.exames.repository.ExameRepository; // Novo import para o repositório de Exame
import br.com.ifba.prg04.paciente.dto.PacienteGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;
import br.com.ifba.prg04.requisicao.entity.Requisicao;
import br.com.ifba.prg04.requisicao.repository.RequisicaoRepository;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequisicaoService implements RequisicaoIService {

    private final RequisicaoRepository requisicaoRepository;
    private final PacienteRepository pacienteRepository;
    private final ExameRepository exameRepository; // Novo campo para buscar Exames

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
            requisicao.setExames(exames); // Define a lista de Exame na entidade
        } else {
            requisicao.setExames(Collections.emptyList()); // Lista vazia se não houver exames
        }

        return toDto(requisicaoRepository.save(requisicao));
    }

    @Override
    public List<RequisicaoGetResponseDto> findAll() {
        return requisicaoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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
    public List<RequisicaoGetResponseDto> findByPacienteNome(String nome) {
        return requisicaoRepository.findByPacienteNome(nome).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequisicaoGetResponseDto> findByPacienteCpf(String cpf) {
        return requisicaoRepository.findByPacienteCpf(cpf).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private RequisicaoGetResponseDto toDto(Requisicao requisicao) {
        PacienteGetResponseDto pacienteDto = new PacienteGetResponseDto(
                // requisicao.getPaciente().getNome(),
                // requisicao.getPaciente().getCpf(),
                // requisicao.getPaciente().getDataNascimento(),
                // requisicao.getPaciente().getGenero()
        );

        // Converter List<Exame> para List<String> com as descrições dos exames
        List<String> examesList = (requisicao.getExames() == null || requisicao.getExames().isEmpty())
                ? Collections.emptyList()
                : requisicao.getExames().stream()
                .map(Exame::getDescricao) // Extrai a descrição de cada Exame
                .collect(Collectors.toList());

        return new RequisicaoGetResponseDto(
                requisicao.getId(),
                requisicao.getDataRequisicao(),
                pacienteDto,
                examesList
        );
    }
}