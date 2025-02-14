package br.com.ifba.prg04.atendimento.service;

import br.com.ifba.prg04.atendimento.dto.AtendimentoDTO;
import br.com.ifba.prg04.atendimento.entity.Atendimento;
import br.com.ifba.prg04.atendimento.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    public List<AtendimentoDTO> listarTodos() {
        return atendimentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AtendimentoDTO> buscarPorId(Long id) {
        return atendimentoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Atendimento salvar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public void deletar(Long id) {
        atendimentoRepository.deleteById(id);
    }

    private AtendimentoDTO convertToDTO(Atendimento atendimento) {
        return new AtendimentoDTO(
                atendimento.getId(),
                atendimento.getTipoAtendimento(),
                atendimento.getDataHoraInicio(),
                atendimento.getDataHoraTermino(),
                atendimento.getPaciente().getId(),
                atendimento.getPaciente().getNome()
        );
    }
}
