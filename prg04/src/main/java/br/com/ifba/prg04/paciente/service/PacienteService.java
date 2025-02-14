package br.com.ifba.prg04.paciente.service;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteService implements PacienteIService{

    private final PacienteRepository pacienteRepository;

    @Transactional
    @Override
    public Paciente save(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
            //log.info(paciente.getNome());
           // pacienteRepository.save(paciente);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro de integridade de dados: " + e.getMessage(), e);
            throw new BusinessException("Erro de integridade de dados ao tentar salvar o paciente", e);
        } catch (Exception e) {
            log.error("Erro ao salvar paciente: " + e.getMessage(), e);
            throw new BusinessException("Erro ao tentar salvar os dados do paciente", e);
        }
    }

    @Transactional
    @Override

    //É preciso que ele recebe um string cpf e string nome para fazer a logica de mudar o cpf também se precisar
    public void update(Paciente paciente) {

        try {
            Paciente novosDados = pacienteRepository.findByNomeOrCpf(paciente.getNome(), paciente.getCpf());

            novosDados.setNome(paciente.getNome());
            novosDados.setEmail(paciente.getEmail());
            novosDados.setEndereco(paciente.getEndereco());
            novosDados.setGenero(paciente.getGenero());
            novosDados.setResponsavel(paciente.getResponsavel());
            novosDados.setTelefone(paciente.getTelefone());
            novosDados.setEstadoCivil(paciente.getEstadoCivil());


            if(paciente.getId() != null) {

                pacienteRepository.save(novosDados);
            }
            else
                log.error("Dados do paciente não encontrado");
        }catch (Exception e) {
            throw new BusinessException("Erro ao tentar atualizar os dados do paciente", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findByCpf(String cpf) {
        log.info("Buscando paciente com CPF: {}", cpf);
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn("Paciente não encontrado para o CPF: {}", cpf);
                    return new ResourceNotFoundException("Não foi encontrado nenhum paciente com o CPF: " + cpf);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findById(Long id) {
        log.info("Buscando paciente com ID: {}", id);
        return pacienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Paciente não encontrado para o ID: {}", id);
                    return new ResourceNotFoundException("Não foi encontrado nenhum paciente com o ID: " + id);
                });
    }

    //
//    @Transactional
//    @Override
//    public Prontuario findByPaciente(Paciente paciente) {
//        try {
//            return prontuarioRepository.findByNomeOrCpf(paciente.getNome(), paciente.getCpf());
//        } catch (Exception e) {
//            throw new BusinessException("Erro ao tentar buscar os dados",e);
//        }
//    }

}
