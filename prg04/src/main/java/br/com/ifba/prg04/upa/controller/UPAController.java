package br.com.ifba.prg04.upa.controller;

// Importações necessárias para o controlador
import br.com.ifba.prg04.upa.dto.UPADto;
import br.com.ifba.prg04.upa.dto.UPAGetDto;
import br.com.ifba.prg04.upa.entity.UPA;
import br.com.ifba.prg04.upa.mapper.UPAMapper;
import br.com.ifba.prg04.upa.service.UPAIService;
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
@RequestMapping("/upa")
// Permite requisições de qualquer origem (CORS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
// Gera automaticamente um construtor com os campos finais
@RequiredArgsConstructor
public class UPAController {
    // Serviço injetado para lógica de negócio relacionada a UPA
    private final UPAIService upaIService;
    // Mapeador para conversão entre DTOs e entidades
    private final UPAMapper upaMapper;

    // Endpoint GET para listar todas as UPAs com paginação
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UPAGetDto>> findAll(Pageable pageable) {
        // Busca UPAs paginadas e as converte para DTOs antes de retornar
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.upaIService.findAll(pageable).map(this.upaMapper::toDto));
    }

    // Endpoint POST para criar uma nova UPA
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UPADto upaDto) {
        // Converte o DTO em entidade e salva via serviço
        UPA upa = this.upaMapper.toEntity(upaDto);
        // Retorna o DTO da UPA criada com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.upaMapper.toDto(this.upaIService.save(upa)));
    }

    // Endpoint DELETE para remover uma UPA pelo ID
    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") @NotNull Long id) {
        // Executa a exclusão da UPA pelo serviço
        this.upaIService.delete(id);
        // Retorna status 204 (NO_CONTENT) indicando sucesso sem corpo de resposta
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint PUT para atualizar uma UPA existente
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid UPADto upaDto) {
        // Converte o DTO em entidade e atualiza via serviço
        UPA upa = this.upaMapper.toEntity(upaDto);
        // Retorna o DTO atualizado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.upaMapper.toDto(this.upaIService.update(upa)));
    }
}