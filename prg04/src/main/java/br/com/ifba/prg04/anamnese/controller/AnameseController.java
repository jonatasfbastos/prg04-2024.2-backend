package br.com.ifba.prg04.anamnese.controller;

import br.com.ifba.prg04.anamnese.dto.AnamneseGetResponseDto;
import br.com.ifba.prg04.anamnese.dto.AnamnesePostResponseDto;
import br.com.ifba.prg04.anamnese.entity.Anamnese;
import br.com.ifba.prg04.anamnese.service.AnamneseIService;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anamnese")
@RequiredArgsConstructor
public class AnameseController {

    private final AnamneseIService anamneseService;
    private final ObjectMapperUtil objectMapperUtil;


    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid AnamnesePostResponseDto anamnesePostResponseDto,
                                  @RequestParam Long prontuarioId) {

        // Converte o DTO para a entidade Anamnese
        Anamnese anamnese = objectMapperUtil.map(anamnesePostResponseDto, Anamnese.class);

        // Chama o serviço passando a anamnese e o ID do prontuário
        Anamnese anamneseSalva = anamneseService.save(anamnese, prontuarioId);

        // Retorna a resposta convertendo a entidade para o DTO de saída
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(anamneseSalva, AnamneseGetResponseDto.class));
    }



    //Não será necessário por enquanto
//    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> update(@RequestBody @Valid AnamnesePostResponseDto anamnesePostResponseDto) {
//        anamneseService.update(objectMapperUtil.map(anamnesePostResponseDto, Anamnese.class));
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
