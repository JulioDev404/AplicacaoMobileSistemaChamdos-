package com.example.myapplication.models;

import java.util.List;

public class TicketDetalhesResponse {
    public int id;
    public String titulo;
    public String descricao;
    public String status;
    public int prioridade;
    public String criadoEm;
    public String atualizadoEm;
    public List<ComentarioResponse> comentarios;
}