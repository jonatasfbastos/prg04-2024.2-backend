package br.com.ifba.prg04.vacinacao.controllers;


import br.com.ifba.prg04.vacinacao.entities.CarteiraVacinaRetroativa;
import br.com.ifba.prg04.vacinacao.services.CarteiraVacinaRetroativaIService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/vacinas_retroativas")
public class CarteiraVacinaRetroativaController {
    private final CarteiraVacinaRetroativaIService carteiraVacinaRetroativaIService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody CarteiraVacinaRetroativa carteira) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carteiraVacinaRetroativaIService.save(carteira));
    }

    @GetMapping(path = "findByIdPaciente/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByIdPaciente(@PathVariable ("idPaciente") Long idPaciente, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carteiraVacinaRetroativaIService.findByIdPaciente(pageable, idPaciente));
    }

}
