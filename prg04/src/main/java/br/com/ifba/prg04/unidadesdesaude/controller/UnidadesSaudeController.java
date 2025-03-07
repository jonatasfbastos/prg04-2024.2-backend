package br.com.ifba.prg04.unidadesdesaude.controller;

// Importações necessárias para o controlador
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

// Marca a classe como um controlador REST do Spring
@RestController
// Define o caminho base para todas as rotas do controlador
@RequestMapping(path = "/unidades")
// Permite requisições de qualquer origem (CORS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
// Gera automaticamente um construtor com os campos finais
@RequiredArgsConstructor
public class UnidadesSaudeController {
    // Serviço injetado para lógica de negócio relacionada a unidades de saúde
    private final UnidadeSaudeIService unidadeSaudeService;
    // Utilitário para mapeamento entre objetos (DTOs e entidades)
    private final ObjectMapperUtil objectMapperUtil;

    // Endpoint GET para listar todas as unidades de saúde com paginação
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UnidadeSaudeGetResponseDto>> findAll(Pageable pageable) {
        // Busca unidades paginadas e as converte para DTOs de resposta
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.unidadeSaudeService.findAll(pageable)
                        .map(c -> objectMapperUtil.map(c, UnidadeSaudeGetResponseDto.class)));
    }

    // Endpoint GET para buscar uma unidade de saúde por ID
    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value="id") @NotNull Long id) {
        // Busca a unidade pelo ID e a converte para DTO de resposta
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(unidadeSaudeService.findById(id), UnidadeSaudeGetResponseDto.class));
    }

    // Endpoint GET para buscar uma unidade de saúde por nome
    @GetMapping(path = "/findbynome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByNome(@PathVariable(value="nome") @NotNull @NotBlank String nome) {
        // Busca a unidade pelo nome e a converte para DTO de resposta
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(unidadeSaudeService.findByNome(nome), UnidadeSaudeGetResponseDto.class));
    }

    // Endpoint POST para criar uma nova unidade de saúde
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UnidadeSaudeSaveRequestDto unidadeSaudeSaveRequestDto) {
        // Converte o DTO de requisição em entidade, salva e retorna como DTO de resposta
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(
                        unidadeSaudeService.save(objectMapperUtil.map(unidadeSaudeSaveRequestDto, UnidadesSaude.class)),
                        UnidadeSaudeGetResponseDto.class));
    }

    // Endpoint DELETE para remover uma unidade de saúde pelo telefone
    @DeleteMapping(path = "/delete/{telefone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "telefone") String telefone) {
        // Executa a exclusão da unidade pelo serviço usando o telefone como identificador
        unidadeSaudeService.delete(telefone);
        // Retorna status 204 (NO_CONTENT) indicando sucesso sem corpo de resposta
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint PUT para atualizar uma unidade de saúde existente
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid UnidadeSaudePutResquestDto unidadeSaudePutRequestDto) {
        // Converte o DTO de requisição em entidade, atualiza e retorna como DTO de resposta
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(
                        unidadeSaudeService.update(objectMapperUtil.map(unidadeSaudePutRequestDto, UnidadesSaude.class)),
                        UnidadeSaudeGetResponseDto.class));
    }
}