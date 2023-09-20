package com.challenge.forum.dto;

import java.time.LocalDateTime;

import com.challenge.forum.entity.Topicos;
import com.challenge.forum.entity.Curso;

public record ListagemTopicosDTO(Long id, String titulo, String mensagem, 
		LocalDateTime dataCriacao, boolean ativo, String autor, Curso curso ) {
	
	public ListagemTopicosDTO(Topicos topico) {
		this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),
				topico.isAtivo(), topico.getAutor(), topico.getCurso());
		
	}
	
}
