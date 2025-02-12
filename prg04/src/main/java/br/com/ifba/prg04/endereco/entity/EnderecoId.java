
package br.com.ifba.prg04.endereco.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoId implements Serializable {
    private String rua;
    private Integer numero;
    private String cep;

    @Override
    public String toString() {
        return  "Rua: " + rua +
                ", Número: " + numero +
                ", CEP: " + cep;
    }
}
