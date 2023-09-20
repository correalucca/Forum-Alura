package com.challenge.forum.entity;

import com.challenge.forum.dto.TopicosDTO;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity(name = "Topicos")
@Table(name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String autor;
	private String mensagem;
	private boolean ativo;
	
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;	
	@Enumerated(EnumType.STRING)	
	private Curso curso;
	
	public Topicos(@Valid TopicosDTO data) {
		this.ativo = true;
		this.dataCriacao = LocalDateTime.now();
		this.autor = data.autor();
		this.titulo = data.titulo();
		this.mensagem = data.mensagem();
		this.curso = data.curso();
	}

	public void atualizar(TopicosDTO data) {
		if (data.autor() != null) {
			this.autor = data.autor();
		}
		if (data.titulo() != null) {
			this.titulo = data.titulo();
		}
		if (data.mensagem() != null) {
			this.mensagem = data.mensagem();
		}
		if (data.curso() != null) {
			this.curso = data.curso();
		}	
		
	}	

}
