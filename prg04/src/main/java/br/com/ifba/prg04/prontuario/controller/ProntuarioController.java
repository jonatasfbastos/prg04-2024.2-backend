package br.com.ifba.prg04.prontuario.controller;

import br.com.ifba.prg04.prontuario.service.ProntuarioIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prontuario")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioIService prontuarioService;

}
