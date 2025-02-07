
package br.com.ifba.prg04.endereco.controller;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.endereco.service.EnderecoIService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class EnderecoController{
    private final EnderecoIService enderecoService; // Injeta o serviço de enderecos.

}
