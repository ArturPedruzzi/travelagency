package com.api.travelagency.controller;

import com.api.travelagency.model.Destino;
import com.api.travelagency.service.DestinoServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinos")
public class DestinoControlador {
    private final DestinoServico destinoServico = new DestinoServico();

    @PostMapping
    public ResponseEntity<Destino> adicionarDestino(@RequestBody Destino destino) {
        Destino destinoSalvo = destinoServico.adicionarDestino(destino);
        return ResponseEntity.status(201).body(destinoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Destino>> obterTodosDestinos() {
        List<Destino> destinos = destinoServico.obterTodosDestinos();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisarDestinos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {
        List<Destino> resultados = destinoServico.pesquisarDestinos(nome, localizacao);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> obterDestinoPorId(@PathVariable Long id) {
        Optional<Destino> destino = destinoServico.obterDestinoPorId(id);
        return destino.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/avaliar")
    public ResponseEntity<Destino> adicionarAvaliacao(
            @PathVariable Long id,
            @RequestParam int avaliacao) {
        if (avaliacao < 1 || avaliacao > 10) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Destino> destinoAtualizado = destinoServico.adicionarAvaliacaoAoDestino(id, avaliacao);
        return destinoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable Long id) {
        boolean excluido = destinoServico.deletarDestino(id);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}