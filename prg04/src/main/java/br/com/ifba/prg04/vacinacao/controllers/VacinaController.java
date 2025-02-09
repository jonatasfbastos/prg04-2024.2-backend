package br.com.ifba.prg04.vacinacao.controllers;


import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.vacinacao.dto.VacinaGetResponseDto;
import br.com.ifba.prg04.vacinacao.dto.VacinaPostRequestDto;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import br.com.ifba.prg04.vacinacao.services.VacinaIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

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
    public ResponseEntity<?> update(@PathVariable ("id") Long id
            , @RequestBody Vacina vacina) {
        vacina.setId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}