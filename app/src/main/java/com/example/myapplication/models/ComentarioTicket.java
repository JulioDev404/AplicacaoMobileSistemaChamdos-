package com.example.myapplication.models;

import java.time.format.DateTimeFormatter;

public class ComentarioTicket {
    private int id;
    private int ticketid;
    private int usuarioid;
    private String comentario;
    private boolean visivelSolicitante;
    private DateTimeFormatter criadoEm;

    public int getId() {
        return id;
    }

    public int getTicketid() {
        return ticketid;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean isVisivelSolicitante() {
        return visivelSolicitante;
    }

    public DateTimeFormatter getCriadoEm() {
        return criadoEm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setVisivelSolicitante(boolean visivelSolicitante) {
        this.visivelSolicitante = visivelSolicitante;
    }

    public void setCriadoEm(DateTimeFormatter criadoEm) {
        this.criadoEm = criadoEm;
    }
}
