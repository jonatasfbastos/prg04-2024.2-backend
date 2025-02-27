package br.com.ifba.prg04.funcionario.controllers;

import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.funcionario.service.FuncionarioIService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/funcionarios")
@RequiredArgsConstructor
@CrossOrigin
public class FuncionarioController {

    private final IFuncionarioService service;
    private final ObjectMapperUtil objectMapperUtil;

    /**
     * Endpoint para salvar um novo funcionário.
     */
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postFuncionario(@RequestBody @Valid FuncionarioPostRequestDtos funcionarioPostRequestDtos) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ObjectMapperUtil.map(this.service.save(objectMapperUtil.map(funcionarioPostRequestDtos, Funcionario.class)),
                        FuncionarioGetResponseDtos.class));
    }

    /**
     * Endpoint para buscar todos os funcionários paginados.
     */
    @GetMapping(path = "/findall")
    public ResponseEntity<Page<FuncionarioGetResponseDtos>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.findAll(pageable)
                        .map(c -> ObjectMapperUtil.map(c, FuncionarioGetResponseDtos.class)));
    }

    /**
     * Endpoint para deletar um funcionário pelo ID.
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para atualizar um funcionário existente.
     */
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody @Valid FuncionarioPostRequestDtos funcionarioPostRequestDtos) {
        Funcionario novoFuncionario = service.update(id, objectMapperUtil.map(funcionarioPostRequestDtos, Funcionario.class));
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(novoFuncionario, FuncionarioGetResponseDtos.class));
    }

    /**
     * Endpoint para buscar um funcionário pelo ID.
     */
    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(this.service.findById(id), FuncionarioGetResponseDtos.class));
    }

    /**
     * Endpoint para buscar funcionários por nome.
     */
    @GetMapping(path = "/findbynome/{nome}")
    public ResponseEntity<Page<FuncionarioGetResponseDtos>> findByNome(@PathVariable("nome") String nome, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.service.findByNome(nome, pageable)
                        .map(c -> ObjectMapperUtil.map(c, FuncionarioGetResponseDtos.class)));
    }

    /**
     * Endpoint para buscar funcionários por CPF.
     */
    @GetMapping(path = "/findbycpf/{cpf}")
    public ResponseEntity<?> findByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(this.service.findByCpf(cpf), FuncionarioGetResponseDtos.class));
    }
}