package br.com.ifba.prg04.medicamento.controller;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.medicamento.dtos.MedicamentoGetResponseDtos;
import br.com.ifba.prg04.medicamento.dtos.MedicamentoPostRequestDtos;
import br.com.ifba.prg04.medicamento.entity.Medicamento;
import br.com.ifba.prg04.medicamento.service.IMedicamentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(path = "/medicamentos") // Define o caminho base para todas as requisições
@RequiredArgsConstructor
@CrossOrigin // Permite requisições de diferentes origens (CORS)
public class MedicamentoController {

    private final IMedicamentoService service;
    private final ObjectMapperUtil objectMapperUtil; // Utilitário para conversão de objetos (DTOs e Entidades)

    /**
     * Endpoint para salvar um novo medicamento.
     * Recebe um DTO de requisição, converte para a entidade, salva e retorna um DTO de resposta.
     */
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postMedicamento(@RequestBody @Valid MedicamentoPostRequestDtos medicamentoPostRequestDtos) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ObjectMapperUtil.map(this.service.save(objectMapperUtil.map(medicamentoPostRequestDtos, Medicamento.class)),
                        MedicamentoGetResponseDtos.class));
    }

    /**
     * Endpoint para buscar todos os medicamentos paginados.
     */
    @GetMapping(path = "/findall")
    public ResponseEntity<Page<MedicamentoGetResponseDtos>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.findAll(pageable)
                        .map(c -> ObjectMapperUtil.map(c, MedicamentoGetResponseDtos.class)));
    }

    /**
     * Endpoint para deletar um medicamento pelo ID.
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna status 204 (No Content) caso a deleção seja bem-sucedida
    }

    /**
     * Endpoint para atualizar um medicamento existente.
     * Recebe um ID e um DTO de atualização, converte para a entidade, atualiza e retorna um DTO de resposta.
     */
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody @Valid MedicamentoPostRequestDtos medicamentoPostRequestDtos) {
        Medicamento novoMedicamento = service.update(id, objectMapperUtil.map(medicamentoPostRequestDtos, Medicamento.class));
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(novoMedicamento, MedicamentoGetResponseDtos.class));
    }
}
