package br.com.ifba.prg04.vacinacao.controllers;


import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.vacinacao.dto.VacinaGetResponseDto;
import br.com.ifba.prg04.vacinacao.dto.VacinaPostRequestDto;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import br.com.ifba.prg04.vacinacao.services.VacinaIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
