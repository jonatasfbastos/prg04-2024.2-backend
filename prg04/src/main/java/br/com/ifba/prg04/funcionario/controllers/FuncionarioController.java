package br.com.ifba.prg04.funcionario.controllers;


import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.funcionario.dto.FuncionarioPostRequestDto;
import br.com.ifba.prg04.funcionario.dto.FuncionarioGetResponseDto;
import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.funcionario.service.FuncionarioService;
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

    private final FuncionarioService service;
    private final ObjectMapperUtil objectMapperUtil;

    /**
     * Endpoint para salvar um novo funcionário.
     */
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioGetResponseDto> postFuncionario(@RequestBody @Valid FuncionarioPostRequestDto funcionarioPostRequestDto) {
        // Corrigido: passamos o DTO para o serviço
        Funcionario funcionario = objectMapperUtil.map(funcionarioPostRequestDto, Funcionario.class);
        this.service.saveFuncionario(funcionario);
        
        // Mapeando o objeto Funcionario para FuncionarioGetResponseDto
        FuncionarioGetResponseDto responseDto = objectMapperUtil.map(funcionario, FuncionarioGetResponseDto.class);
        
        // Retornando o ResponseEntity com o status CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    /**
     * Endpoint para buscar todos os funcionários paginados.
     */
    @GetMapping(path = "/findall")
    public ResponseEntity<Page<FuncionarioGetResponseDto>> findAll(Pageable pageable) {
        Page<Funcionario> funcionarios = this.service.getAllFuncionarios(pageable);
        Page<FuncionarioGetResponseDto> response = funcionarios.map(funcionario -> objectMapperUtil.map(funcionario, FuncionarioGetResponseDto.class));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint para deletar um funcionário pelo ID.
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        this.service.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para atualizar um funcionário existente.
     */
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioGetResponseDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid FuncionarioPostRequestDto funcionarioPostRequestDto) {
        Funcionario funcionarioAtualizado = service.updateFuncionario(id, objectMapperUtil.map(funcionarioPostRequestDto, Funcionario.class));
        FuncionarioGetResponseDto responseDto = objectMapperUtil.map(funcionarioAtualizado, FuncionarioGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    /**
     * Endpoint para buscar um funcionário pelo ID.
     */
    @GetMapping(path = "/findbyid/{id}")
    public ResponseEntity<FuncionarioGetResponseDto> findById(@PathVariable("id") Long id) {
        Funcionario funcionario = this.service.getFuncionarioById(id);
        FuncionarioGetResponseDto responseDto = objectMapperUtil.map(funcionario, FuncionarioGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


        
}


