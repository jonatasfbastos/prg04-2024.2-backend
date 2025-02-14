package br.com.ifba.prg04.anamnese.service;

import br.com.ifba.prg04.anamnese.entity.Anamnese;
import br.com.ifba.prg04.anamnese.repository.AnamneseRepository;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import br.com.ifba.prg04.prontuario.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnamneseService implements AnamneseIService{

    private final AnamneseRepository anamneseRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    @Transactional
    public Anamnese save(Anamnese anamnese, Long prontuarioId) {
        try {
            // Busca o prontuário pelo ID
            Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                    .orElseThrow(() -> new BusinessException("Prontuário não encontrado"));

            // Associa a anamnese ao prontuário
            anamnese.setProntuario(prontuario);

            // Salva a anamnese no banco de dados
            return anamneseRepository.save(anamnese);
        } catch (Exception e) {
            throw new BusinessException("Erro ao salvar os dados de anamnese", e);
        }
    }


//    @Override
//    @Transactional
//    public void update(Anamnese anamnese) {
//
//    }
}
