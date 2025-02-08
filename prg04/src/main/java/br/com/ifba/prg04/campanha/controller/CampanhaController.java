package br.com.ifba.prg04.campanha.controller;

import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.service.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/campanha")
@Controller
public class CampanhaController {

    @Autowired
    private CampanhaService cs;

    @GetMapping("/findall")
    public List<Campanha> findAll() {
        return cs.findAll();
    }

    @PostMapping("/save")
    public Campanha save(Campanha c) {
        return cs.save(c);
    }
}
