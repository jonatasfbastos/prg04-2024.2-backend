
package br.com.ifba.prg04.endereco.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="enderecos")
public class Endereco{
    // Configurando o ID composto
    @EmbeddedId
    private EnderecoId id;

    // Campos individuais
    private String complemento;
    private String bairro;
    private String cidade;

    @Column(length = 2)
    private String uf;

    // Construtor que inicializa o ID composto
    public Endereco(String rua, int numero, String cep, String complemento, String bairro, String cidade, String uf) {
        this.id = new EnderecoId(rua, numero, cep);
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}
