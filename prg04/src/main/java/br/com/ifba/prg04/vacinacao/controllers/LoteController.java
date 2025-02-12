package br.com.ifba.prg04.vacinacao.controllers;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.vacinacao.dto.LotePostRequestDto;
import br.com.ifba.prg04.vacinacao.entities.Lote;
import br.com.ifba.prg04.vacinacao.services.LoteIservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findall(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.loteIservice.findAllLote(pageable)
                        .map(c -> objectMapperUtil.map(c, LotePostRequestDto.class)));
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        loteIservice.deleteLoteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable ("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil
                        .map(loteIservice.findLoteById(id), LotePostRequestDto.class));
    }

    @PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody @Valid Lote lote) {
        lote.setId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(objectMapperUtil
                        .map(loteIservice.updateLote(objectMapperUtil
                                .map(lote, Lote.class)), LotePostRequestDto.class));
    }
}