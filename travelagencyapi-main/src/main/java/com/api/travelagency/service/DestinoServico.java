package com.api.travelagency.service;

import com.api.travelagency.model.Destino;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DestinoServico {
    private List<Destino> destinos = new ArrayList<>();
    private Long proximoId = 1L;

    public Destino adicionarDestino(Destino destino) {
        destino.setId(proximoId++);
        destinos.add(destino);
        return destino;
    }

    public List<Destino> obterTodosDestinos() {
        return destinos;
    }

    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinos.stream()
                .filter(destino -> (nome == null || destino.getNome().equalsIgnoreCase(nome)) &&
                        (localizacao == null || destino.getLocalizacao().equalsIgnoreCase(localizacao)))
                .collect(Collectors.toList());
    }

    public Optional<Destino> obterDestinoPorId(Long id) {
        return destinos.stream()
                .filter(destino -> destino.getId().equals(id))
                .findFirst();
    }

    public Optional<Destino> adicionarAvaliacaoAoDestino(Long id, int avaliacao) {
        Optional<Destino> destinoOpt = obterDestinoPorId(id);

        if (destinoOpt.isPresent()) {
            Destino destino = destinoOpt.get();
            destino.adicionarAvaliacao(avaliacao);
            return Optional.of(destino);
        }
        return Optional.empty();
    }

    public boolean deletarDestino(Long id) {
        Optional<Destino> destino = obterDestinoPorId(id);
        if (destino.isPresent()) {
            destinos.remove(destino.get());
            return true;
        }
        return false;
    }
}