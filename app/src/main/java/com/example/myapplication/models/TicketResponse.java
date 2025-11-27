package com.example.myapplication.models;

public class TicketResponse {
    private int id;
    private String titulo;
    private String descricao;
    private int solicitanteId;
    private String status;      // TicketStatus (enum: 1=A,2=..., etc.)
    private String prioridade;  // PriorityLevel (enum)
    private String criadoEm;
    private String atualizadoEm;

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }

    public int getSolicitanteId() { return solicitanteId; }
    public void setSolicitanteId(int solicitanteId) { this.solicitanteId = solicitanteId; }

    public String getStatus() { return status; }

    public String getPrioridade() { return prioridade; }

    public String getCriadoEm() { return criadoEm; }

    public String getAtualizadoEm() { return atualizadoEm; }
}

