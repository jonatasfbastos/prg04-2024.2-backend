
package br.com.ifba.prg04.endereco.service;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import java.util.List;

public interface EnderecoIService {
    Endereco save(Endereco endereco) throws RuntimeException;
    Endereco update(Endereco endereco) throws RuntimeException;
    void delete(Endereco endereco) throws RuntimeException;
    Endereco findById(EnderecoId id) throws RuntimeException;
    List<Endereco> findAll() throws RuntimeException;
}