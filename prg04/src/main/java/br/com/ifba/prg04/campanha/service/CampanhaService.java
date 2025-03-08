package br.com.ifba.prg04.campanha.service;

import br.com.ifba.prg04.campanha.entity.Campanha;
import br.com.ifba.prg04.campanha.repository.CampanhaRepository;
import br.com.ifba.prg04.vacina.entity.Vacina;
import br.com.ifba.prg04.vacina.repository.VacinaIRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampanhaService {

    private final CampanhaRepository cr;
    private final VacinaIRepository vacinaRepository; // Adicionando o repositório de Vacina

    public CampanhaService(CampanhaRepository cr, VacinaIRepository vacinaRepository) {
        this.cr = cr;
        this.vacinaRepository = vacinaRepository;
    }

    public List<Campanha> findAll(){
        return cr.findAll();
    }

    public Campanha save(Campanha campanha){
        return cr.save(campanha);
    }
}
