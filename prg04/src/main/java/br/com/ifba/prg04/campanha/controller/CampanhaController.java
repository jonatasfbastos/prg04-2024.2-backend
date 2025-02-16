package br.com.ifba.prg04.campanha.controller;

import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.repository.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/campanha")
@RestController
public class CampanhaController {

    @Autowired
    private CampanhaRepository campanhaR;


    @PostMapping("/save")
    public Campanha save(@RequestBody Campanha campanha) {
        return campanhaR.save(campanha);
    }

    @GetMapping("/findall")
    public List<Campanha> findall() {
        return (List<Campanha>)campanhaR.findAll();
    }

    @PutMapping("/update")
    public Campanha update(@RequestBody Campanha campanha) {
        return campanhaR.save(campanha);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<Campanha> delete (@PathVariable Long id) {
        Optional <Campanha> campanha = campanhaR.findById(id);
        if (campanha.isPresent()) {
            campanhaR.deleteById(id);
        } else {
            return Optional.empty();
        }
        return campanha;
    }
}
