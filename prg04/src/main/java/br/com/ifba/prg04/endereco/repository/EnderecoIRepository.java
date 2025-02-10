
package br.com.ifba.prg04.endereco.repository;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoIRepository extends JpaRepository<Endereco, EnderecoId>{
}
