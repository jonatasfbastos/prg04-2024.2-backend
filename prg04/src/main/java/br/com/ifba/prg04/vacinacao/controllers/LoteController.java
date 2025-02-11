package br.com.ifba.prg04.vacinacao.controllers;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.vacinacao.dto.LotePostRequestDto;
import br.com.ifba.prg04.vacinacao.entities.Lote;
import br.com.ifba.prg04.vacinacao.services.LoteIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lotes")
@RequiredArgsConstructor
public class LoteController {
    private final LoteIservice loteIservice;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody LotePostRequestDto lotePostRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loteIservice.saveLote(
                        objectMapperUtil.map(lotePostRequestDto, Lote.class)));
    }
}
