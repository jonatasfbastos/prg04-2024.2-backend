package br.com.ifba.prg04.campanha.controller;

import br.com.ifba.prg04.campanha.dto.CampanhaGetResponseDto;
import br.com.ifba.prg04.campanha.dto.CampanhaPostRequestDto;
import br.com.ifba.prg04.campanha.dto.CampanhaPutRequestDto;
import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.repository.CampanhaRepository;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/campanha")
@RestController
@RequiredArgsConstructor
public class CampanhaController {

    @Autowired
    private final CampanhaRepository campanhaR;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody CampanhaPostRequestDto campanhaPostRequestDto) {
        Campanha campanha = objectMapperUtil.map(campanhaPostRequestDto, Campanha.class);
        Campanha savedCampanha = campanhaR.save(campanha);
        CampanhaGetResponseDto responseDto = objectMapperUtil.map(savedCampanha, CampanhaGetResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findall(Pageable pageable) {
        Page<Campanha> campanhas = campanhaR.findAll(pageable);

        List<CampanhaGetResponseDto> campanhasDto = campanhas.stream()
                .map(c -> objectMapperUtil.map(c, CampanhaGetResponseDto.class))
                .collect(Collectors.toList());

        Map<String, Object> response = Map.of(
                "campanhas", campanhasDto,
                "total", campanhas.getTotalElements()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody CampanhaPutRequestDto campanhaPutRequestDto) {
        Campanha campanha = objectMapperUtil.map(campanhaPutRequestDto, Campanha.class);
        Campanha updatedCampanha = campanhaR.save(campanha);
        CampanhaGetResponseDto responseDto = objectMapperUtil.map(updatedCampanha, CampanhaGetResponseDto.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Campanha> campanha = campanhaR.findById(id);

        if (campanha.isPresent()) {
            campanhaR.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 No Content - deletado com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Campanha com ID " + id + " não encontrada.");
        }
    }

    @GetMapping(path = "/findbyvacina/{vacina}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findbyvacina(@PathVariable String vacina, Pageable pageable) {
        Page<Campanha> campanhas = campanhaR.findByVacina(vacina, pageable);

        Page<CampanhaGetResponseDto> campanhasDto = campanhas.map(
                campanha -> objectMapperUtil.map(campanha, CampanhaGetResponseDto.class)
        );

        return ResponseEntity.status(HttpStatus.OK).body(campanhasDto);
    }

}
