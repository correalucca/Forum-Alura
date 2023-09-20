package com.challenge.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.forum.dto.ListagemTopicosDTO;
import com.challenge.forum.dto.TopicosDTO;
import com.challenge.forum.entity.Topicos;
import com.challenge.forum.repository.TopicosRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicosService {
    @Autowired
    private TopicosRepository repository;

    @Transactional
    public void salvar(TopicosDTO dados) {
        TopicosDTO topicoExistente = repository.findByTitulo(dados.titulo());

        if (topicoExistente == null) {
            repository.save(new Topicos(dados));
        } else {
            throw new RuntimeException("Já existe um tópico com o mesmo título");
        }
    }

    public Page<ListagemTopicosDTO> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(ListagemTopicosDTO::new);
    }

    public ListagemTopicosDTO detalhar(Long id) {
        Topicos topico = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id));
        return new ListagemTopicosDTO(topico);
    }

    @Transactional
    public ListagemTopicosDTO atualizar(Long id, TopicosDTO dados) {
        TopicosDTO topicoExistente = repository.findByTitulo(dados.titulo());

        Topicos topico = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id));

        if (topicoExistente == null) {
            topico.atualizar(dados);
            return new ListagemTopicosDTO(repository.save(topico));
        } else {
            throw new RuntimeException("Já existe um tópico com o mesmo título!");
        }
    }

    @Transactional
    public void deletar(Long id) {
        repository.findById(id)
            .ifPresentOrElse(
                topico -> repository.delete(topico),
                () -> {
                    throw new EntityNotFoundException("Tópico não encontrado com o ID: " + id);
                }
            );
    }
}
