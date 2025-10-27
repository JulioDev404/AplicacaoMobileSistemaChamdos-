package com.example.myapplication.models;

public class LoginResponse {
    private int id;
    private String nome;
    private String email;
    private String tipoUsuario;

    public int getId(){return this.id;}
    public String getNome(){return this.nome;}
    public String getEmail(){return this.email;}
    public String getTipoUsuario(){return  this.tipoUsuario;}

}
