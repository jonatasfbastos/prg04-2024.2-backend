package br.com.ifba.prg04.gestaoatendimento.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoPost;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.gestaoatendimento.exceptions.HandleGenericException;
import br.com.ifba.prg04.gestaoatendimento.exceptions.SchedulingDuplicateException;
import br.com.ifba.prg04.gestaoatendimento.exceptions.SchedulingNotFoundException;
import br.com.ifba.prg04.gestaoatendimento.exceptions.UniqueCodeViolationException;
import br.com.ifba.prg04.gestaoatendimento.repository.GestaoAtendimentoRepository;
import br.com.ifba.prg04.usuario.entity.Usuario;
import br.com.ifba.prg04.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestaoAtendimentoService {
 private final GestaoAtendimentoRepository gestaoAtendimentoRepository;
 private final UsuarioRepository usuarioRepository;

 
 public Page<GestaoAtendimento> findall(Pageable pageable){
    return gestaoAtendimentoRepository.findAll(pageable);
 }
 // metodo para listar agendamentos marcados por um mesmo usuario
 public List<GestaoAtendimento> findAtendimentos(String nomeUser){
   List<GestaoAtendimento> list = gestaoAtendimentoRepository.findByUsuarioNome(nomeUser);
   if(list==null){
      throw new SchedulingNotFoundException("Agendamentos não econtrados");
   }
   return list;
 }
 public GestaoAtendimento findbycode(String code){
    GestaoAtendimento atendimento = gestaoAtendimentoRepository.findAtendimentoByCode(code);
    if(atendimento!= null){
      return atendimento;
    }
    throw new SchedulingNotFoundException("Agendamento nao encontrado");
 }
 // metodo para salvar
 @Transactional
 public GestaoAtendimento save(DtoAtendimentoPost dto){
   Usuario usuario = usuarioRepository.findByNome(dto.getUsuarioNome());
   GestaoAtendimento atendimento = new GestaoAtendimento();
   if(usuario!=null){
      // setando dados do atendimento
   atendimento.setCode(dto.getCode());
   atendimento.setDataHora(dto.getDataHora());
   atendimento.setEspecialidadeMedica(dto.getEspecialidadeMedica());
   atendimento.setUsuario(usuario);
    if(gestaoAtendimentoRepository.existsAtendimentoByCode(atendimento.getCode())){
      throw new UniqueCodeViolationException("O codigo nao pode ser duplicado");
    }
     if(gestaoAtendimentoRepository.existsAtendimentoBydataHora(atendimento.getDataHora())){
        throw new SchedulingDuplicateException("Ja tem um agendamento no mesmo horario e data");
     }
  // se passar nas verificações salva o atendimento
   return gestaoAtendimentoRepository.save(atendimento);
   }
  // senao lança uma excecao
   throw new HandleGenericException("Erro ao salvar");
 }
// metodo update
@Transactional
 public GestaoAtendimento update(DtoAtendimentoPost update, String code) {
   // verifico se já tem um agendamento com mesma data e horario
   if(gestaoAtendimentoRepository.existsAtendimentoBydataHora(update.getDataHora())){
      throw new SchedulingDuplicateException("Ja tem um agendamento no mesmo horario e data");
   }
   // busco o agendamento na base de dados para atualizacao
   GestaoAtendimento atual = gestaoAtendimentoRepository.findAtendimentoByCode(code);
   if(atual!= null){
      // so podera atualizar a data horario e especialidade medica
      atual.setDataHora(update.getDataHora());
      atual.setEspecialidadeMedica(update.getEspecialidadeMedica());
      gestaoAtendimentoRepository.save(atual);
      return atual;
   }
   throw new HandleGenericException("Erro ao atualizar");
}
//metodo delete
@Transactional
 public void delete(String code){
   gestaoAtendimentoRepository.deleteAtendimentoByCode(code);
    
 }

}
