package br.com.ifba.prg04.campanha.service;

import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.repository.CampanhaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
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

    public Page<Campanha> findByVacina(String vacina, Pageable pageable){
        return cr.findByVacina(vacina, pageable);
    }
}
