package com.example.myapplication.models;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String depatamento;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDepatamento() {
        return depatamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepatamento(String depatamento) {
        this.depatamento = depatamento;
    }

}
