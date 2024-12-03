package com.api.travelagency.model;

import jakarta.persistence.*;

@Entity
@Table(name = "destinos")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String localizacao;

    @Column(length = 500)
    private String descricao;

    @Column(name = "avaliacao_media", nullable = false)
    private double avaliacaoMedia = 0.0;

    @Column(name = "quantidade_avaliacoes", nullable = false)
    private int quantidadeAvaliacoes = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void adicionarAvaliacao(int novaAvaliacao) {
        if (novaAvaliacao < 1 || novaAvaliacao > 10) {
            throw new IllegalArgumentException("A avaliação deve estar entre 1 e 10.");
        }
        // Atualiza a média e incrementa a quantidade de avaliações
        avaliacaoMedia = ((avaliacaoMedia * quantidadeAvaliacoes) + novaAvaliacao) / ++quantidadeAvaliacoes;
    }
}
