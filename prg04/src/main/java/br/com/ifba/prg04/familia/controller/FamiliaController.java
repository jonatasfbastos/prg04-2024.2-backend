package br.com.ifba.prg04.familia.controller;

import br.com.ifba.prg04.familia.dto.FamiliaGetResponseDTO;
import br.com.ifba.prg04.familia.dto.FamiliaPostRequestDTO;
import br.com.ifba.prg04.familia.dto.FamiliaPutResquestDTO;
import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.service.FamiliaService;
import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.funcionario.repositories.FuncionarioRepository;
import br.com.ifba.prg04.funcionario.service.FuncionarioService;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/familias")
@RequiredArgsConstructor
public class FamiliaController {

    private final FamiliaService familiaService;
    private final ObjectMapperUtil objectMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioService funcionarioService;

    /*@PostMapping(path = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FamiliaPostRequestDTO> save(@Valid @RequestBody FamiliaPostRequestDTO familiaPost ) {
        Familia familia = objectMapper.map(familiaPost, Familia.class);
        Familia familiaSalva = familiaService.save(familia);

        FamiliaPostRequestDTO familiaResponse = objectMapper.map(familiaSalva, FamiliaPostRequestDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(familiaResponse);
    }*/

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FamiliaPostRequestDTO> save(@Valid @RequestBody FamiliaPostRequestDTO familiaPost) {
        // Busca o funcionário pelo ID
        Funcionario responsavel = funcionarioService.getFuncionarioById(familiaPost.getResponsavel_id());

        if (responsavel == null) {
            throw new RuntimeException("Funcionario nao encontrado");
        }

        // Mapeia o DTO para a entidade
        Familia familia = new Familia();
        familia.setNome(familiaPost.getNome());
        familia.setEndereco(familiaPost.getEndereco());
        familia.setResponsavel(responsavel); // Atribui o objeto Funcionario
        familia.setMembros(familiaPost.getMembros());

        // Salva a família
        Familia familiaSalva = familiaService.save(familia);

        // Mapeia a entidade salva para o DTO de resposta
        FamiliaPostRequestDTO familiaResponse = new FamiliaPostRequestDTO();
        familiaResponse.setNome(familiaSalva.getNome());
        familiaResponse.setEndereco(familiaSalva.getEndereco());
        familiaResponse.setResponsavel_id(familiaSalva.getResponsavel().getId()); // Retorna o ID do responsável
        familiaResponse.setMembros(familiaSalva.getMembros());

        return ResponseEntity.status(HttpStatus.CREATED).body(familiaResponse);
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FamiliaPutResquestDTO> familiaUpdate(@PathVariable Long id, @RequestBody FamiliaPutResquestDTO familiaPut) {
        Familia familia = familiaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Familia nao encontrada"));

        familia.setNome(familiaPut.getNome());
        familia.setEndereco(familiaPut.getEndereco());
        familia.setMembros(familiaPut.getMembros());

        if (familiaPut.getResponsavelId() != null) {
            Funcionario responsavel = funcionarioRepository.findById(familiaPut.getResponsavelId())
                    .orElseThrow(() -> new RuntimeException("Responsavel nao encontrado com o id: " + familiaPut.getResponsavelId()));
            familia.setResponsavel(responsavel);
        }
        // Atualiza a família usando o serviço
        Familia familiaAtualizada = familiaService.update(id, familia);

        // Converte a entidade atualizada para DTO
        FamiliaPutResquestDTO response = new FamiliaPutResquestDTO();
        response.setNome(familiaAtualizada.getNome());
        response.setEndereco(familiaAtualizada.getEndereco());
        response.setMembros(familiaAtualizada.getMembros());
        response.setResponsavelId(familiaAtualizada.getResponsavel().getId());

        // Retorna a resposta com status 200 OK
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/findByName/{nome}")
    public ResponseEntity<List<Familia>> findByName(@PathVariable String nome){
        List<Familia> familias = familiaService.findByName(nome);
        return ResponseEntity.status(HttpStatus.OK).body(familias);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<Page<Familia>> findAll(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Familia> familiaList = familiaService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(familiaList);
    }
}
