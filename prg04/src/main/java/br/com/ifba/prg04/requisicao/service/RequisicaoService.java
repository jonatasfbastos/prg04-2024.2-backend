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
        // Busca o paciente pelo ID
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado!"));

        // Cria a entidade RequisicaoEntity com a lista de exames
        RequisicaoEntity requisicao = new RequisicaoEntity();
        requisicao.setDataRequisicao(dto.getDataRequisicao());
        requisicao.setPaciente(paciente);
        requisicao.setExames(dto.getExames()); // Define a lista de exames

        // Salva a requisição no banco de dados
        requisicao = requisicaoRepository.save(requisicao);

        // Converte a entidade para DTO de resposta
        return toDto(requisicao);
    }

    @Override
    public List<RequisicaoGetResponseDto> findAll() {
        // Busca todas as requisições e converte para DTO
        return requisicaoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequisicaoGetResponseDto findById(Long id) {
        // Busca uma requisição pelo ID e converte para DTO
        RequisicaoEntity requisicao = requisicaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada!"));
        return toDto(requisicao);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Verifica se a requisição existe antes de deletar
        if (!requisicaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Requisição não encontrada!");
        }
        requisicaoRepository.deleteById(id);
    }

    @Override
    public List<RequisicaoGetResponseDto> findByPacienteNome(String nome) {
        // Busca requisições pelo nome do paciente e converte para DTO
        return requisicaoRepository.findByPacienteNome(nome).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequisicaoGetResponseDto> findByPacienteCpf(String cpf) {
        // Busca requisições pelo CPF do paciente e converte para DTO
        return requisicaoRepository.findByPacienteCpf(cpf).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Método auxiliar para converter RequisicaoEntity para RequisicaoGetResponseDto
    private RequisicaoGetResponseDto toDto(RequisicaoEntity requisicao) {
        PacienteGetResponseDto pacienteDto = new PacienteGetResponseDto(
                requisicao.getPaciente().getNome(),
                requisicao.getPaciente().getCpf(),
                requisicao.getPaciente().getDataNascimento(),
                requisicao.getPaciente().getGenero()
        );

        return new RequisicaoGetResponseDto(
                requisicao.getId(),
                requisicao.getDataRequisicao(),
                pacienteDto,
                requisicao.getExames() // Passa a lista de exames
        );
    }
}