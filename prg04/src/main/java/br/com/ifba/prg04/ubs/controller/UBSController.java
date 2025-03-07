package br.com.ifba.prg04.ubs.controller;

// Importações necessárias para o controlador
import br.com.ifba.prg04.ubs.dto.UBSDto;
import br.com.ifba.prg04.ubs.dto.UBSGetDto;
import br.com.ifba.prg04.ubs.dto.UBSPutDto;
import br.com.ifba.prg04.ubs.entity.UBS;
import br.com.ifba.prg04.ubs.mapper.UBSMapper;
import br.com.ifba.prg04.ubs.service.UBSIService;
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
@RequestMapping(path = "/ubs")
// Gera automaticamente um construtor com os campos finais
@RequiredArgsConstructor
// Permite requisições de qualquer origem (CORS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UBSController {
    // Serviço injetado para lógica de negócio relacionada a UBS
    private final UBSIService ubsIService;
    // Mapeador para conversão entre DTOs e entidades
    private final UBSMapper ubsMapper;

    // Endpoint GET para listar todas as UBS com paginação
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UBSGetDto>> findAll(Pageable pageable) {
        // Busca UBS paginadas e as converte para DTOs antes de retornar
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.ubsIService.findAll(pageable).map(this.ubsMapper::toDto));
    }

    // Endpoint POST para criar uma nova UBS
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UBSDto ubsDto) {
        // Converte o DTO em entidade e salva via serviço
        UBS ubs = this.ubsMapper.toEntity(ubsDto);
        // Retorna o DTO da UBS criada com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.ubsMapper.toDto(this.ubsIService.save(ubs)));
    }

    // Endpoint DELETE para remover uma UBS pelo ID
    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") @NotNull Long id) {
        // Executa a exclusão da UBS pelo serviço
        this.ubsIService.delete(id);
        // Retorna status 204 (NO_CONTENT) indicando sucesso sem corpo de resposta
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint PUT para atualizar uma UBS existente
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid UBSPutDto ubsDto) {
        // Converte o DTO em entidade e atualiza via serviço
        UBS ubs = this.ubsMapper.toEntity(ubsDto);
        // Retorna o DTO atualizado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.ubsMapper.toDto(this.ubsIService.update(ubs)));
    }
}