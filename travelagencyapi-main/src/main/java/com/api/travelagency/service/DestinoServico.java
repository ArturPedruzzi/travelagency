package com.api.travelagency.service;

import com.api.travelagency.model.Destino;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DestinoServico {
    private List<Destino> destinos = new ArrayList<>(); // Lista para armazenar os destinos
    private Long proximoId = 1L; // Controle do próximo ID a ser atribuído

    // Método para adicionar um destino à lista
    public Destino adicionarDestino(Destino destino) {
        destino.setId(proximoId++); // Atribui um ID único ao destino
        destinos.add(destino); // Adiciona o destino à lista
        return destino;
    }

    // Método para obter todos os destinos
    public List<Destino> obterTodosDestinos() {
        return destinos;
    }

    // Método para pesquisar destinos com base no nome e localizacao
    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinos.stream()
                .filter(d -> (nome == null || d.getNome().equalsIgnoreCase(nome)) &&
                        (localizacao == null || d.getLocalizacao().equalsIgnoreCase(localizacao)))
                .collect(Collectors.toList()); // Retorna a lista filtrada de destinos
    }

    // Método para obter um destino pelo ID
    public Optional<Destino> obterDestinoPorId(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst(); // Retorna o destino se encontrado
    }

    // Método para adicionar uma avaliação a um destino
    public Optional<Destino> adicionarAvaliacaoAoDestino(Long id, int avaliacao) {
        return obterDestinoPorId(id).map(d -> { // Se o destino existir
            d.adicionarAvaliacao(avaliacao); // Adiciona a avaliação ao destino
            return d;
        });
    }

    // Método para deletar um destino
    public boolean deletarDestino(Long id) {
        return obterDestinoPorId(id).map(d -> destinos.remove(d)).orElse(false); // Remove o destino se encontrado
    }
}
