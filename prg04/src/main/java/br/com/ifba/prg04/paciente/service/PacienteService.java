package br.com.ifba.prg04.paciente.service;

import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Service
@RequiredArgsConstructor
public class PacienteService implements PacienteIService{

    private final PacienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Paciente findByCpf(String cpf) {
        log.info("Buscando paciente com CPF: {}", cpf);
        return repository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn("Paciente não encontrado para o CPF: {}", cpf);
                    return new ResourceNotFoundException("Não foi encontrado nenhum paciente com o CPF: " + cpf);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findById(Long id) {
        log.info("Buscando paciente com ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Paciente não encontrado para o ID: {}", id);
                    return new ResourceNotFoundException("Não foi encontrado nenhum paciente com o ID: " + id);
                });
    }
}
