package br.com.ifba.prg04.gestaoatendimento.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ifba.prg04.gestaoatendimento.dto.GestaoAtendimentoPostRequestDto;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.gestaoatendimento.exceptions.HandleGenericException;
import br.com.ifba.prg04.gestaoatendimento.exceptions.SchedulingDuplicateException;
import br.com.ifba.prg04.gestaoatendimento.exceptions.SchedulingNotFoundException;
import br.com.ifba.prg04.gestaoatendimento.exceptions.UniqueCodeViolationException;
import br.com.ifba.prg04.gestaoatendimento.repository.GestaoAtendimentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;

@Service
@RequiredArgsConstructor
public class GestaoAtendimentoService {
 private final GestaoAtendimentoRepository gestaoAtendimentoRepository;
 private final ObjectMapperUtil mapper;
 public Page<GestaoAtendimento> findall(Pageable pageable){
    return gestaoAtendimentoRepository.findAll(pageable);
 }
 public GestaoAtendimento findbycodigo(String code){
    GestaoAtendimento atendimento = gestaoAtendimentoRepository.findGestaoAtendimentoByCodigo(code);
    if(atendimento!= null){
      return atendimento;
    }
    throw new SchedulingNotFoundException("Agendamento nao encontrado");
 }
 // metodo para salvar
 @Transactional
 public GestaoAtendimento save(GestaoAtendimentoPostRequestDto dto){
  GestaoAtendimento atendimento = mapper.map(dto, GestaoAtendimento.class);
    if(gestaoAtendimentoRepository.existsGestaoAtendimentoByCodigo(atendimento.getCodigo())){
      throw new UniqueCodeViolationException("O codigo nao pode ser duplicado");
    }
     if(gestaoAtendimentoRepository.existsGestaoAtendimentoBydataHora(atendimento.getDataHora())){
        throw new SchedulingDuplicateException("Ja tem um agendamento no mesmo horario e data");
     }
  // se passar nas verificações salva o atendimento
   return gestaoAtendimentoRepository.save(atendimento);
   }
  
 
// metodo update
@Transactional
 public GestaoAtendimento update(GestaoAtendimentoPostRequestDto update, String code) {
   // busco o agendamento na base de dados para atualizacao
   GestaoAtendimento atual = gestaoAtendimentoRepository.findGestaoAtendimentoByCodigo(code);
   GestaoAtendimento atendimentoUpdate = mapper.map(update, GestaoAtendimento.class);
   if(atual!= null){
      // atualizando
      atendimentoUpdate.setId(atual.getId());
      gestaoAtendimentoRepository.save(atual);
      return atual;
   }
   throw new HandleGenericException("Erro ao atualizar");
}
//metodo delete
@Transactional
 public void delete(String code){
   gestaoAtendimentoRepository.deleteGestaoAtendimentoByCodigo(code);
    
 }

}
