package br.com.ifba.prg04.farmacia.controller;

// Importações necessárias para o controlador
import br.com.ifba.prg04.farmacia.dto.FarmaciaDto;
import br.com.ifba.prg04.farmacia.dto.FarmaciaGetDto;
import br.com.ifba.prg04.farmacia.entity.Farmacia;
import br.com.ifba.prg04.farmacia.mapper.FarmaciaMapper;
import br.com.ifba.prg04.farmacia.service.FarmaciaIService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Marca a classe como um controlador REST do Spring
@RestController
// Define o caminho base para todas as rotas do controlador
@RequestMapping("/farmacias")
// Permite requisições de qualquer origem (CORS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
// Gera automaticamente um construtor com os campos finais
@RequiredArgsConstructor
public class FarmaciaController {
    // Serviço de farmácia injetado para operações de negócio
    private final FarmaciaIService farmaciaIService;
    // Mapeador para conversão entre DTOs e entidades
    private final FarmaciaMapper farmaciaMapper;

    // Endpoint GET para listar todas as farmácias com paginação
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<FarmaciaGetDto>> findAll(Pageable pageable) {
        // Busca farmácias paginadas e as converte para DTOs antes de retornar
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.farmaciaIService.findAll(pageable).map(this.farmaciaMapper::toDto));
    }

    // Endpoint POST para criar uma nova farmácia
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid FarmaciaDto farmaciaDto) {
        // Converte o DTO recebido em entidade e salva no banco
        Farmacia farmacia = this.farmaciaMapper.toEntity(farmaciaDto);
        // Retorna o DTO da farmácia criada com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.farmaciaMapper.toDto(this.farmaciaIService.save(farmacia)));
    }

    // Endpoint DELETE para remover uma farmácia pelo ID
    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") @NotNull Long id) {
        // Executa a exclusão da farmácia pelo serviço
        this.farmaciaIService.delete(id);
        // Retorna status 204 (NO_CONTENT) indicando sucesso sem corpo de resposta
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint PUT para atualizar uma farmácia existente
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid FarmaciaDto farmaciaDto) {
        // Converte o DTO em entidade e atualiza via serviço
        Farmacia farmacia = this.farmaciaMapper.toEntity(farmaciaDto);
        // Retorna o DTO atualizado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.farmaciaMapper.toDto(this.farmaciaIService.update(farmacia)));
    }
}