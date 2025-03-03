package br.com.ifba.prg04.vacina.controller;


import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.vacina.dto.VacinaGetResponseDto;
import br.com.ifba.prg04.vacina.dto.VacinaPostRequestDto;
import br.com.ifba.prg04.vacina.entity.Vacina;
import br.com.ifba.prg04.vacina.service.VacinaIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/vacinas")
@RequiredArgsConstructor
public class VacinaController {
    private final VacinaIService vacinaIService;

    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody VacinaPostRequestDto vacinaPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil
                        .map(vacinaIService.save(objectMapperUtil
                                .map(vacinaPostRequestDto, Vacina.class)), VacinaGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findall(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.vacinaIService.findAll(pageable)
                        .map(c -> objectMapperUtil.map(c, VacinaGetResponseDto.class)));
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        vacinaIService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable ("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil
                        .map(vacinaIService.findById(id), VacinaGetResponseDto.class));
    }

    @PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody @Valid Vacina vacina) {
        vacina.setId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(objectMapperUtil
                        .map(vacinaIService.updateVacina(objectMapperUtil
                                .map(vacina, Vacina.class)), VacinaGetResponseDto.class));
    }

    @GetMapping(path = "findByDoenca/{doencaCombatida}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByDoencaCombatida(@PathVariable ("doencaCombatida") String doencaCombatida,
                                                   Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.vacinaIService.findByDoencaCombatida(doencaCombatida, pageable)
                        .map(c -> objectMapperUtil.map(c, VacinaGetResponseDto.class)));
    }

    @GetMapping(path = "findByDataVencimento/{dataVencimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByDataVencimento(@PathVariable ("dataVencimento") LocalDate dataVencimento,
                                                   Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.vacinaIService.findByDataVencimento(dataVencimento, pageable)
                        .map(c -> objectMapperUtil.map(c, VacinaGetResponseDto.class)));
    }
}