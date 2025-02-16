package br.com.ifba.prg04.familia.controller;

import br.com.ifba.prg04.familia.dto.FamiliaGetResponseDTO;
import br.com.ifba.prg04.familia.dto.FamiliaPostRequestDTO;
import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.service.FamiliaService;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/familias")
@RequiredArgsConstructor
public class FamiliaController {

    private final FamiliaService familiaService;
    private final ObjectMapperUtil objectMapper;

    @PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FamiliaPostRequestDTO> save(@Valid @RequestBody FamiliaPostRequestDTO familiaPost ) {
        Familia familia = objectMapper.map(familiaPost, Familia.class);
        Familia familiaSalva = familiaService.save(familia);

        FamiliaPostRequestDTO familiaResponse = objectMapper.map(familiaSalva, FamiliaPostRequestDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(familiaResponse);
    }



}
