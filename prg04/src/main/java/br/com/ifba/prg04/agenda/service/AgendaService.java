package br.com.ifba.prg04.agenda.service;

import br.com.ifba.prg04.agenda.dto.AgendaPostRequestDto;
import br.com.ifba.prg04.agenda.entity.Agenda;
import br.com.ifba.prg04.agenda.repository.AgendaRepository;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgendaService implements AgendaIService {

    //Utilizando log para registrar as informações
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaService.class);

    //Injeção de dependência
    private final AgendaRepository agendaRepository;

    //Método para buscar todos as agendas
    public Page<Agenda> findAll(Pageable pageable) {
        LOGGER.info("Buscando todos os usuários");
        return agendaRepository.findAll(pageable);
    }

    //Salvando agenda com transação e vinculando a usuario
    @Transactional
    public Agenda save(Agenda agenda) {
        LOGGER.info("Salvando Agenda");
        return agendaRepository.save(agenda);
    }

    //Buscando agenda por ID
    public Agenda findById(Long id) {
        return agendaRepository.findById(id).orElseThrow(() -> new BusinessException("Agenda não encontrada"));
    }

    //Deletando Agenda com ID
    public void delete(Long id) {
        agendaRepository.deleteById(id);
    }

    //Atualizando Agenda com ID
    public Agenda update(Long id, AgendaPostRequestDto agendaPostRequestDto) {
        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agenda não encontrada"));

        agendaExistente.setTitulo(agendaPostRequestDto.getTitulo());
        agendaExistente.setDescricao(agendaPostRequestDto.getDescricao());
        agendaExistente.setDataHoraInicio(agendaPostRequestDto.getDataHoraInicio());
        agendaExistente.setDataHoraFim(agendaPostRequestDto.getDataHoraFim());
        agendaExistente.setCancelado(agendaPostRequestDto.isCancelado());

        return agendaRepository.save(agendaExistente);
    }

    //Cancelando Agenda com ID (Verificar depois se é necessário deixar implementada)
    @Transactional
    public Agenda cancelar(Long id) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Agenda não encontrada"));

        if (agenda.isCancelado()) {
            throw new BusinessException("A agenda já está cancelada.");
        }

        agenda.setCancelado(true);
        return agendaRepository.save(agenda);
    }

}
