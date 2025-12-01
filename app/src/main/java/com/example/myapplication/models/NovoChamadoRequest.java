package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class NovoChamadoRequest {

    // O ID é o campo mais importante, pois confirma a criação no DB
    @SerializedName("Id")
    private int id;

    // Dados que você enviou
    @SerializedName("Titulo")
    private String titulo;

    @SerializedName("Descricao")
    private String descricao;

    @SerializedName("Prioridade")
    private String prioridade;

    // Campos gerados pelo Servidor/DB
    @SerializedName("Status")
    private String status; // Se TicketStatus for um Enum, o JSON o serializa como string

    @SerializedName("CriadoEm")
    private String criadoEm; // Tipicamente retornado como String formatada (ISO 8601)

    public NovoChamadoRequest(String titulo, String descricao, String prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    // Opcional: Para ter acesso ao ID no Android
    public int getId() {
        return id;
    }

    // Getters e Setters para os outros campos (pode ser necessário para o Gson)
    // ...

    @Override
    public String toString() {
        return "Chamado #" + id + ": " + titulo + " (" + status + ")";
    }
}