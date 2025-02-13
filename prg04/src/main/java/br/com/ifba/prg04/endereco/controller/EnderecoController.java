
package br.com.ifba.prg04.endereco.controller;

import br.com.ifba.prg04.endereco.dto.EnderecoDto;
import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.endereco.service.EnderecoIService;
import java.util.List;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeSaveRequestDto;
import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class EnderecoController{
    private final EnderecoIService enderecoService; // Injeta o serviço de enderecos.
    private final ObjectMapperUtil objectMapperUtil;

    @GetMapping(path = "/findbyid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@Valid EnderecoId id) {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(enderecoService.findById(id), EnderecoDto.class));
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid EnderecoDto enderecoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(objectMapperUtil.map(enderecoDto, Endereco.class)));
    }

//    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> delete(@PathVariable("id") @NotNull @NotEmpty Long id) {
//        unidadeSaudeService.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

//    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> update(@RequestBody @Valid UnidadeSaudeSaveRequestDto unidadeSaudeSaveRequestDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(unidadeSaudeService.update(objectMapperUtil.map(unidadeSaudeSaveRequestDto, UnidadesSaude.class)), UnidadeSaudeGetResponseDto.class));
//    }
}
