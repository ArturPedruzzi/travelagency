package com.api.travelagency.service;

import com.api.travelagency.model.Destino;
import com.api.travelagency.repository.DestinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoServico {

    private final DestinoRepository destinoRepository;

    // Injeção de dependência via construtor
    public DestinoServico(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    public Destino adicionarDestino(Destino destino) {
        return destinoRepository.save(destino); // Salva o destino no banco
    }

    public List<Destino> obterTodosDestinos() {
        return destinoRepository.findAll(); // Retorna todos os destinos
    }

    public Optional<Destino> obterDestinoPorId(Long id) {
        return destinoRepository.findById(id); // Busca um destino pelo ID
    }

    public boolean deletarDestino(Long id) {
        if (destinoRepository.existsById(id)) {
            destinoRepository.deleteById(id); // Deleta um destino pelo ID
            return true;
        }
        return false;
    }

    public Optional<Destino> adicionarAvaliacaoAoDestino(Long id, int avaliacao) {
        return obterDestinoPorId(id).map(destino -> {
            destino.adicionarAvaliacao(avaliacao); // Atualiza a avaliação
            return destinoRepository.save(destino); // Salva as alterações
        });
    }

    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        // Aqui você pode criar consultas personalizadas no repositório, mas como exemplo:
        return destinoRepository.findAll().stream()
                .filter(d -> (nome == null || d.getNome().equalsIgnoreCase(nome)) &&
                        (localizacao == null || d.getLocalizacao().equalsIgnoreCase(localizacao)))
                .toList(); // Retorna uma lista filtrada
    }
}
