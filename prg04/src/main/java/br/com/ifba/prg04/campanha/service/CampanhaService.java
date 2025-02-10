package br.com.ifba.prg04.campanha.service;

import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.repository.CampanhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampanhaService {

    private CampanhaRepository cr;

    public List<Campanha> findAll(){
        return cr.findAll();
    }

    public Campanha save(Campanha campanha){
        return cr.save(campanha);
    }
}
