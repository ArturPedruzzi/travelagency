package com.api.travelagency.model;

public class Destino {
    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private double avaliacaoMedia;
    private int quantidadeAvaliacoes;

    public Destino() {
        this.avaliacaoMedia = 0.0;
        this.quantidadeAvaliacoes = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getAvaliacaoMedia() { return avaliacaoMedia; }

    public void adicionarAvaliacao(int novaAvaliacao) {
        if (novaAvaliacao < 1 || novaAvaliacao > 10) {
            throw new IllegalArgumentException("A avaliação deve estar entre 1 e 10.");
        }

        avaliacaoMedia = ((avaliacaoMedia * quantidadeAvaliacoes) + novaAvaliacao) / (quantidadeAvaliacoes + 1);
        quantidadeAvaliacoes++;
    }
}
