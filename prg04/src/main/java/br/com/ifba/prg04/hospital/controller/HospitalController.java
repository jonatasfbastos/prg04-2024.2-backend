package br.com.ifba.prg04.hospital.controller;

// Importações necessárias para o controlador
import br.com.ifba.prg04.hospital.dto.HospitalDto;
import br.com.ifba.prg04.hospital.dto.HospitalGetDto;
import br.com.ifba.prg04.hospital.entity.Hospital;
import br.com.ifba.prg04.hospital.mapper.HospitalMapper;
import br.com.ifba.prg04.hospital.service.HospitalIService;
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
@RequestMapping("/hospitais")
// Permite requisições de qualquer origem (CORS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
// Gera automaticamente um construtor com os campos finais
@RequiredArgsConstructor
public class HospitalController {
    // Serviço injetado para lógica de negócio relacionada a hospitais
    private final HospitalIService hospitalService;
    // Mapeador para conversão entre DTOs e entidades
    private final HospitalMapper hospitalMapper;

    // Endpoint GET para listar todos os hospitais com paginação
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<HospitalGetDto>> findAll(Pageable pageable) {
        // Busca hospitais paginados e os converte para DTOs antes de retornar
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.hospitalService.findAll(pageable).map(this.hospitalMapper::toDto));
    }

    // Endpoint POST para criar um novo hospital
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid HospitalDto hospitalDto) {
        // Converte o DTO em entidade e salva via serviço
        Hospital hospital = this.hospitalMapper.toEntity(hospitalDto);
        // Retorna o DTO do hospital criado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.hospitalMapper.toDto(this.hospitalService.save(hospital)));
    }

    // Endpoint DELETE para remover um hospital pelo ID
    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") @NotNull Long id) {
        // Executa a exclusão do hospital pelo serviço
        this.hospitalService.delete(id);
        // Retorna status 204 (NO_CONTENT) indicando sucesso sem corpo de resposta
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint PUT para atualizar um hospital existente
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid HospitalDto hospitalDto) {
        // Converte o DTO em entidade e atualiza via serviço
        Hospital hospital = this.hospitalMapper.toEntity(hospitalDto);
        // Retorna o DTO atualizado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.hospitalMapper.toDto(this.hospitalService.update(hospital)));
    }
}