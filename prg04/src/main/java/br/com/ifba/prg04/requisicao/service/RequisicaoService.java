package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.paciente.dto.PacienteGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;
import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import br.com.ifba.prg04.requisicao.repository.RequisicaoRepository;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequisicaoService implements RequisicaoIService {

    private final RequisicaoRepository requisicaoRepository;
    private final PacienteRepository pacienteRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository, PacienteRepository pacienteRepository) {
        this.requisicaoRepository = requisicaoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    @Transactional
    public RequisicaoGetResponseDto save(RequisicaoPostRequestDto dto) {
        Paciente paciente = pacienteRepository.findByCpf(dto.getCpfPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com CPF " + dto.getCpfPaciente() + " não encontrado!"));

        RequisicaoEntity requisicao = new RequisicaoEntity();
        requisicao.setDataRequisicao(dto.getDataRequisicao());
        requisicao.setPaciente(paciente);

        // Se houver exames, salvar corretamente no formato string
        if (dto.getExames() != null && !dto.getExames().isEmpty()) {
            requisicao.setExames(String.join(",", dto.getExames()));
        } else {
            requisicao.setExames("");  // Garante que o banco não armazene null
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

    private RequisicaoGetResponseDto toDto(RequisicaoEntity requisicao) {
        PacienteGetResponseDto pacienteDto = new PacienteGetResponseDto(
//                requisicao.getPaciente().getNome(),
//                requisicao.getPaciente().getCpf(),
//                requisicao.getPaciente().getDataNascimento(),
//                requisicao.getPaciente().getGenero()
        );

        // Se a string de exames for vazia, retorna uma lista vazia em vez de tentar split()
        List<String> examesList = (requisicao.getExames() == null || requisicao.getExames().isEmpty())
                ? Collections.emptyList()
                : Arrays.asList(requisicao.getExames().split(","));

        return new RequisicaoGetResponseDto(
                requisicao.getId(),
                requisicao.getDataRequisicao(),
                pacienteDto,
                examesList
        );
    }
}
