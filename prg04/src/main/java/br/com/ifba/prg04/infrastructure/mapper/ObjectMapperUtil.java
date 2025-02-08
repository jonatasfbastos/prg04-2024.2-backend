package br.com.ifba.prg04.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {
    private static final ModelMapper MODEL_MAPPER; // Instância única de ModelMapper.

    static {
        MODEL_MAPPER = new ModelMapper(); // Inicializa a instância do ModelMapper no bloco estático.
    }

    public <Input, Output> Output map(final Input object,
                                      final Class<Output> clazz) {
        // Método genérico para mapear um objeto de entrada para um objeto de saída.

        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true) // Ignora ambiguidades nos mapeamentos.
                .setMatchingStrategy(MatchingStrategies.STRICT) // Define a estratégia de correspondência como estrita.
                .setFieldMatchingEnabled(true) // Habilita a correspondência de campos.
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); // Permite acesso a campos privados.

        Output c = MODEL_MAPPER.map(object, clazz); // Converte o objeto de entrada para a classe de saída especificada.

        return c; // Retorna o objeto convertido.
    }

    public <Input, Output> List<Output> mapAll(final List<Input> list, final Class<Output> clazz) {
        // Método para converter uma lista de objetos de entrada para uma lista de objetos de saída.

        return list.stream()
                .map(item -> this.map(item, clazz)) // Mapeia cada elemento da lista usando o método 'map'.
                .collect(Collectors.toList()); // Coleta os elementos convertidos em uma nova lista.
    }
}
