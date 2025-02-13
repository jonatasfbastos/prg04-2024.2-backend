package br.com.ifba.prg04.agenda.dto.mapper;

import br.com.ifba.prg04.agenda.dto.AgendaGetResponseDto;
import br.com.ifba.prg04.agenda.dto.AgendaPostRequestDto;
import br.com.ifba.prg04.agenda.entity.Agenda;

public class AgendaMapper {

    //Mapea agenda com usuario para interligar no Get e Post
    public static AgendaGetResponseDto mapToDto(Agenda agenda) {
        AgendaGetResponseDto dto = new AgendaGetResponseDto();
        dto.setId(agenda.getId());
        dto.setTitulo(agenda.getTitulo());
        dto.setDescricao(agenda.getDescricao());
        dto.setDataHoraInicio(agenda.getDataHoraInicio());
        dto.setDataHoraFim(agenda.getDataHoraFim());
        dto.setCancelado(agenda.isCancelado());
        if (agenda.getUsuario() != null) {
            dto.setNomeUsuario(agenda.getUsuario().getNome());
        }
        return dto;
    }

    //Entidade de Agenda
    public static Agenda mapToEntity(AgendaPostRequestDto dto) {
        Agenda agenda = new Agenda();
        agenda.setTitulo(dto.getTitulo());
        agenda.setDescricao(dto.getDescricao());
        agenda.setDataHoraInicio(dto.getDataHoraInicio());
        agenda.setDataHoraFim(dto.getDataHoraFim());
        agenda.setCancelado(dto.isCancelado());
        return agenda;
    }

}
