package br.com.ifba.prg04.prontuario.service;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import br.com.ifba.prg04.prontuario.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProntuarioService implements ProntuarioIService{

    private final ProntuarioRepository prontuarioRepository;

    @Transactional
    @Override
    public void save(Prontuario prontuario) {

        try {
            prontuarioRepository.save(prontuario);
        }catch (Exception e) {
            throw new BusinessException("Erro ao tentar salvar os dados", e);
        }
    }




    @Transactional
    @Override
    public void update(Paciente paciente) {

        Prontuario prontuario = new Prontuario();

        try {
            //prontuario = prontuarioRepository.findByNomeOrCpf(paciente.getNome(), paciente.getCpf());

            if( true /*prontuarioRepository.findaById(prontuario.getId())*/) {
                prontuario.setPaciente(paciente);

                prontuarioRepository.save(prontuario);
            }
            else
                log.error("Dados do paciente não encontrado");
        }catch (Exception e) {
            throw new BusinessException("Erro ao tentar atualizar os dados do paciente", e);
        }
    }


}
