package com.example.myapplication.models;

public class LoginRequest {
    private String login;
    private String senha;

    public LoginRequest(String login,String senha){
        this.login = login;
        this.senha = senha;
    }
}
