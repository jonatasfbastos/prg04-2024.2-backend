package br.com.ifba.prg04.agenda.controller;

import br.com.ifba.prg04.agenda.dto.AgendaGetResponseDto;
import br.com.ifba.prg04.agenda.dto.AgendaPostRequestDto;
import br.com.ifba.prg04.agenda.dto.mapper.AgendaMapper;
import br.com.ifba.prg04.agenda.entity.Agenda;
import br.com.ifba.prg04.agenda.service.AgendaIService;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.usuario.entity.Usuario;
import br.com.ifba.prg04.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaIService agendaService;
    private final ObjectMapperUtil objectMapperUtil;
    private final UsuarioIService usuarioService;

    //Salvando agenda com nome de usuario
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid AgendaPostRequestDto agendaPostRequestDto) {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Usuario> usuariosPage = usuarioService.findByNome(agendaPostRequestDto.getNomeUsuario(), pageable);

        if (usuariosPage.hasContent()) {
            Usuario usuario = usuariosPage.getContent().get(0);

            Agenda agenda = AgendaMapper.mapToEntity(agendaPostRequestDto);
            agenda.setUsuario(usuario);
            Agenda salvarAgenda = agendaService.save(agenda);
            AgendaGetResponseDto responseDto = AgendaMapper.mapToDto(salvarAgenda);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
        }
    }

    //Atualizando agenda com nome de usuario
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody AgendaPostRequestDto agendaPostRequestDto) {
        Agenda agendaAtualizada = agendaService.update(id, agendaPostRequestDto);

        AgendaGetResponseDto responseDto = objectMapperUtil.map(agendaAtualizada, AgendaGetResponseDto.class);
        responseDto.setNomeUsuario(agendaAtualizada.getUsuario() != null ? agendaAtualizada.getUsuario().getNome() : null);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }



    //Deletando agenda com ID
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        agendaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("LOG", "Agenda excluída com sucesso"));
    }

    //Buscando todas as agendas
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AgendaGetResponseDto>> findAll(Pageable pageable) {
        Page<Agenda> agendas = agendaService.findAll(pageable);
        Page<AgendaGetResponseDto> agendaDtos = agendas.map(AgendaMapper::mapToDto);
        return ResponseEntity.status(HttpStatus.OK).body(agendaDtos);
    }

    //Cancelando agenda com ID
    @PatchMapping(path = "/cancelar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cancel(@PathVariable("id") Long id) {
        Agenda agendaCancelada = agendaService.cancelar(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(agendaCancelada, AgendaGetResponseDto.class));
    }
}
