package br.com.ifba.prg04.unidadesdesaude.controller;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudePutResquestDto;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeSaveRequestDto;
import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import br.com.ifba.prg04.unidadesdesaude.service.UnidadeSaudeIService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/unidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UnidadesSaudeController {
    private final UnidadeSaudeIService unidadeSaudeService;
    private final ObjectMapperUtil objectMapperUtil;

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UnidadeSaudeGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.unidadeSaudeService.findAll(pageable).map(c -> objectMapperUtil.map(c, UnidadeSaudeGetResponseDto.class)));
    }

    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value="id") @NotNull Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(unidadeSaudeService.findById(id), UnidadeSaudeGetResponseDto.class));
    }

    @GetMapping(path = "/findbynome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByNome(@PathVariable(value="nome") @NotNull @NotBlank String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(unidadeSaudeService.findByNome(nome), UnidadeSaudeGetResponseDto.class));
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UnidadeSaudeSaveRequestDto unidadeSaudeSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(unidadeSaudeService.save(objectMapperUtil.map(unidadeSaudeSaveRequestDto, UnidadesSaude.class)), UnidadeSaudeGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{telefone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "telefone") String telefone) {
        unidadeSaudeService.delete(telefone);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid UnidadeSaudePutResquestDto unidadeSaudePutRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(unidadeSaudeService.update(objectMapperUtil.map(unidadeSaudePutRequestDto, UnidadesSaude.class)), UnidadeSaudeGetResponseDto.class));
    }
}
