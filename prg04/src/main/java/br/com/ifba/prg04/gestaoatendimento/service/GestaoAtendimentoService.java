package br.com.ifba.prg04.gestaoatendimento.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.gestaoatendimento.repository.GestaoAtendimentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestaoAtendimentoService {
 private final GestaoAtendimentoRepository gestaoAtendimentoRepository;

 
 public Page<GestaoAtendimento> findall(Pageable pageable){
    return gestaoAtendimentoRepository.findAll(pageable);
 } 
 public GestaoAtendimento findbycode(String code){
    GestaoAtendimento atendimento = gestaoAtendimentoRepository.findAtendimentoByCode(code);
    if(atendimento!= null){
      return atendimento;
    }
    throw new RuntimeException("atendimento nao encontrado");
 }
 // metodo para salvar
 @Transactional
 public GestaoAtendimento save(GestaoAtendimento atendimento){
   if(gestaoAtendimentoRepository.existsAtendimentoByCode(atendimento.getCode())){
    throw new RuntimeException("O código não pode ser duplicado.");
   }
   if(gestaoAtendimentoRepository.existsAtendimentoBydataHora(atendimento.getDataHora())){
      throw new RuntimeException("Já tem um agendamento no mesmo horario e data");
   }

    return gestaoAtendimentoRepository.save(atendimento);
 }
// metodo update
@Transactional
 public GestaoAtendimento update(GestaoAtendimento update, String code) {
   if(gestaoAtendimentoRepository.existsAtendimentoBydataHora(update.getDataHora())){
      throw new RuntimeException("Já tem um agendamento no mesmo horario e data");
   }
   GestaoAtendimento atual = gestaoAtendimentoRepository.findAtendimentoByCode(code);
   if(atual!= null){
      // so podera atualizar a data horario e especialidade medica
      atual.setDataHora(update.getDataHora());
      atual.setEspecialidadeMedica(update.getEspecialidadeMedica());
      gestaoAtendimentoRepository.save(atual);
      return atual;
   }
   throw new RuntimeException("Erro ao atualizar");
}
//metodo delete
@Transactional
 public void delete(String code){
    gestaoAtendimentoRepository.deleteAtendimentoByCode(code);
 }

}
