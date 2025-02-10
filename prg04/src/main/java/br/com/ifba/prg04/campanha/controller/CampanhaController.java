package br.com.ifba.prg04.campanha.controller;

import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.repository.CampanhaRepository;
import br.com.ifba.prg04.campanha.service.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/campanha")
@RestController
public class CampanhaController {

    @Autowired
    private CampanhaRepository campanhaR;

    @GetMapping("/findall")
    public List<Campanha> findall() {
        return (List<Campanha>) campanhaR.findAll();
    }
}
