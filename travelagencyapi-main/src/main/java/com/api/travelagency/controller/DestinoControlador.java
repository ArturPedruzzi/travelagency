package com.api.travelagency.controller;

import com.api.travelagency.model.Destino;
import com.api.travelagency.service.DestinoServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoControlador {

    private final DestinoServico destinoServico;

    // Injeção de dependência via construtor
    public DestinoControlador(DestinoServico destinoServico) {
        this.destinoServico = destinoServico;
    }

    @PostMapping
    public ResponseEntity<Destino> adicionarDestino(@RequestBody Destino destino) {
        return ResponseEntity.status(201).body(destinoServico.adicionarDestino(destino));
    }

    @GetMapping
    public ResponseEntity<List<Destino>> obterTodosDestinos() {
        return ResponseEntity.ok(destinoServico.obterTodosDestinos());
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam(required = false) String nome,
                                                           @RequestParam(required = false) String localizacao) {
        return ResponseEntity.ok(destinoServico.pesquisarDestinos(nome, localizacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> obterDestinoPorId(@PathVariable Long id) {
        return destinoServico.obterDestinoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/avaliar")
    public ResponseEntity<Destino> adicionarAvaliacao(@PathVariable Long id, @RequestParam int avaliacao) {
        if (avaliacao < 1 || avaliacao > 10) {
            return ResponseEntity.badRequest().build();
        }
        return destinoServico.adicionarAvaliacaoAoDestino(id, avaliacao)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable Long id) {
        if (destinoServico.deletarDestino(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
