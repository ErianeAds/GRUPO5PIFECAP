package com.example.projetocomedoria.model;

public class Transacao {
    private String descricao;
    private String data;
    private String valor;
    private String cor;

    public Transacao(String descricao, String data, String valor, String cor) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.cor = cor;
    }

    // Getters
    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getValor() { return valor; }
    public String getCor() { return cor; }
}