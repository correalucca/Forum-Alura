package com.challenge.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.challenge.forum.dto.ListagemTopicosDTO;
import com.challenge.forum.dto.TopicosDTO;
import com.challenge.forum.service.TopicosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
public class TopicosController {    
    @Autowired
    private TopicosService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@RequestBody @Valid TopicosDTO dados) {
        service.salvar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTopicosDTO>> listar(@PageableDefault(
        size = 10, sort = {"dataCriacao"}, direction = Direction.ASC) Pageable paginacao) {
        Page<ListagemTopicosDTO> topicos = service.listar(paginacao);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemTopicosDTO> detalhar(@PathVariable Long id) {
        ListagemTopicosDTO topico = service.detalhar(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ListagemTopicosDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicosDTO dados) {
        ListagemTopicosDTO topicoAtualizado = service.atualizar(id, dados);
        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
