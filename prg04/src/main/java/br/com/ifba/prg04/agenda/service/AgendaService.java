package br.com.ifba.prg04.agenda.service;

import br.com.ifba.prg04.agenda.entity.Agenda;
import br.com.ifba.prg04.agenda.repository.AgendaRepository;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.usuario.repository.UsuarioRepository;
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
    public Agenda update(Long id, Agenda agenda) {
        Agenda agendaSalva = agendaRepository.findById(id).orElseThrow(() -> new BusinessException("Agenda não encontrada"));
        agendaSalva.setTitulo(agenda.getTitulo());
        agendaSalva.setDescricao(agenda.getDescricao());
        agendaSalva.setDataHoraInicio(agenda.getDataHoraInicio());
        agendaSalva.setDataHoraFim(agenda.getDataHoraFim());
        agendaSalva.setCancelado(agenda.isCancelado());
        return agendaRepository.save(agendaSalva);
    }

    //Cancelando Agenda com ID (Verificar depois se é necessário deixar implementada
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
