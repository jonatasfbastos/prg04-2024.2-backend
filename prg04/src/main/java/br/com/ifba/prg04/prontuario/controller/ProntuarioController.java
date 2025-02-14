package br.com.ifba.prg04.prontuario.controller;

import br.com.ifba.prg04.infrastructure.dto.PageableDto;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.infrastructure.mapper.PageableMapper;
import br.com.ifba.prg04.prontuario.dto.ProntuarioGetResponseDto;
import br.com.ifba.prg04.prontuario.dto.ProntuarioPostResponseDto;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import br.com.ifba.prg04.prontuario.service.ProntuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prontuario")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioIService prontuarioService;
    private final ObjectMapperUtil objectMapperUtil;


    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestParam String cpf) {

        Prontuario prontuarioSalvo = prontuarioService.save(cpf);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(prontuarioSalvo, ProntuarioGetResponseDto.class));
    }



    @GetMapping(value = "/find-by-paciente", params = "id")
    public ResponseEntity<PageableDto> findByIdPaciente(@RequestParam("id") Long id, Pageable pageable) {
        Page<ProntuarioPostResponseDto> page = prontuarioService.findByIdPaciente(id, pageable);
        return ResponseEntity.ok(PageableMapper.toDto(page));
    }



}
