package br.com.ifba.prg04.documento.service;

import br.com.ifba.prg04.documento.entity.Documento;
import br.com.ifba.prg04.documento.repository.DocumentoRepository;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import br.com.ifba.prg04.prontuario.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentoService implements  DocumentoIService{

    private final DocumentoRepository documentoRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    public Documento save(Documento documento, Long prontuarioId) {
        try {
            // Busca o prontuário pelo ID
            Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                    .orElseThrow(() -> new BusinessException("Prontuário não encontrado"));

            // Associa o documento ao prontuário
            documento.setProntuario(prontuario);

            // Salva o documento no banco de dados
            return documentoRepository.save(documento);
        } catch (Exception e) {
            throw new BusinessException("Erro ao salvar os dados do documento", e);
        }
    }

}
