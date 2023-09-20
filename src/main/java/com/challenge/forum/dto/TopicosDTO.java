package com.challenge.forum.dto;

import com.challenge.forum.entity.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicosDTO(
	@NotBlank
	String titulo,		
	@NotBlank
	String mensagem,
	@NotBlank
	String autor,
	@NotNull
	Curso curso) {

}
