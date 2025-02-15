package br.com.ifba.prg04.unidadesdesaude.service;

import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnidadeSaudeIService {
    Page<UnidadesSaude> findAll(Pageable pageable);
    UnidadesSaude findById(Long id);
    UnidadesSaude save(UnidadesSaude unidadesSaude);
    void delete(String Telefone);
    UnidadesSaude update(UnidadesSaude unidadesSaude);
    UnidadesSaude findByNome(String nome);
}
