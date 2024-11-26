package com.api.travelagency.controller;

import com.api.travelagency.model.Destino;
import com.api.travelagency.service.DestinoServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoControlador {
    private final DestinoServico destinoServico = new DestinoServico(); // Instancia o serviço de destinos

    // Endpoint para adicionar um destino
    @PostMapping
    public ResponseEntity<Destino> adicionarDestino(@RequestBody Destino destino) {
        return ResponseEntity.status(201).body(destinoServico.adicionarDestino(destino)); // Retorna o destino adicionado
    }

    // Endpoint para obter todos os destinos
    @GetMapping
    public ResponseEntity<List<Destino>> obterTodosDestinos() {
        return ResponseEntity.ok(destinoServico.obterTodosDestinos()); // Retorna todos os destinos cadastrados
    }

    // Endpoint para pesquisar destinos por nome ou localização
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam(required = false) String nome,
                                                           @RequestParam(required = false) String localizacao) {
        return ResponseEntity.ok(destinoServico.pesquisarDestinos(nome, localizacao)); // Retorna a lista filtrada de destinos
    }

    // Endpoint para obter um destino específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Destino> obterDestinoPorId(@PathVariable Long id) {
        return destinoServico.obterDestinoPorId(id)
                .map(ResponseEntity::ok) // Se o destino for encontrado, retorna com sucesso
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrado, retorna 404
    }

    // Endpoint para adicionar uma avaliação a um destino
    @PostMapping("/{id}/avaliar")
    public ResponseEntity<Destino> adicionarAvaliacao(@PathVariable Long id, @RequestParam int avaliacao) {
        if (avaliacao < 1 || avaliacao > 10) { // Verifica se a avaliação está entre 1 e 10
            return ResponseEntity.badRequest().build(); // Retorna erro se a avaliação não for válida
        }
        return destinoServico.adicionarAvaliacaoAoDestino(id, avaliacao)
                .map(ResponseEntity::ok) // Se o destino foi encontrado e a avaliação adicionada, retorna com sucesso
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrado, retorna 404
    }

    // Endpoint para deletar um destino pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable Long id) {
        return destinoServico.deletarDestino(id)
                ? ResponseEntity.noContent().build() // Se deletado com sucesso, retorna 204
                : ResponseEntity.notFound().build(); // Se não encontrado, retorna 404
    }
}
