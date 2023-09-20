package com.challenge.forum.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.forum.dto.TopicosDTO;
import com.challenge.forum.entity.Topicos;

public interface TopicosRepository extends JpaRepository<Topicos, Long>{

	TopicosDTO findByTitulo(String titulo);


}
