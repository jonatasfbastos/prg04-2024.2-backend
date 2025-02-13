package br.com.ifba.prg04.documento.controller;

import br.com.ifba.prg04.documento.dto.DocumentoGetResponseDto;
import br.com.ifba.prg04.documento.dto.DocumentoPostResponseDto;
import br.com.ifba.prg04.documento.entity.Documento;
import br.com.ifba.prg04.documento.service.DocumentoService;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documento")
@RequiredArgsConstructor
public class DocumentoController {

    private final ObjectMapperUtil objectMapperUtil;
    private final DocumentoService documentoService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid DocumentoPostResponseDto documentoPostResponseDto,
                                  @RequestParam Long prontuarioId) {

        // Converte o DTO para a entidade Documento
        Documento documento = objectMapperUtil.map(documentoPostResponseDto, Documento.class);

        // Chama o serviço passando o documento e o ID do prontuário
        Documento documentoSalvo = documentoService.save(documento, prontuarioId);

        // Retorna a resposta convertendo a entidade para o DTO de saída
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(documentoSalvo, DocumentoGetResponseDto.class));
    }

}
