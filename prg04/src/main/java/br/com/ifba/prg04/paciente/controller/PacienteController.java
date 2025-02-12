package br.com.ifba.prg04.paciente.controller;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.paciente.dto.PacienteGetResponseDto;
import br.com.ifba.prg04.paciente.dto.PacientePostResponseDto;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.paciente.service.PacienteIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteIService pacienteService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid PacientePostResponseDto pacientePostResponseDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(pacienteService.save(
                        (objectMapperUtil.map(pacientePostResponseDto, Paciente.class)
                )), PacienteGetResponseDto.class));
    }


    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody @Valid PacientePostResponseDto pacientePostResponseDto) {
        pacienteService.update(objectMapperUtil.map(pacientePostResponseDto, Paciente.class));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
