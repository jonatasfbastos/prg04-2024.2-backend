package br.com.ifba.prg04.exames.controlller;

import br.com.ifba.prg04.exames.dto.ExameGetResponseDto;
import br.com.ifba.prg04.exames.dto.ExamePostRequestDto;
import br.com.ifba.prg04.exames.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity<ExameGetResponseDto> saveExame(@RequestBody ExamePostRequestDto exameDto) {
        ExameGetResponseDto savedExame = exameService.saveExame(exameDto);
        return new ResponseEntity<>(savedExame, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExame(@PathVariable Long id) {
        exameService.deleteExame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ExameGetResponseDto>> getAllExames() {
        List<ExameGetResponseDto> exames = exameService.getAllExames();
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameGetResponseDto> getExameById(@PathVariable Long id) {
        ExameGetResponseDto exame = exameService.getExameById(id);
        return new ResponseEntity<>(exame, HttpStatus.OK);
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<ExameGetResponseDto>> getExameByDescricao(@PathVariable String descricao) {
        List<ExameGetResponseDto> exames = exameService.getExameByDescricao(descricao);
        return new ResponseEntity<>(exames, HttpStatus.OK);
    }
}