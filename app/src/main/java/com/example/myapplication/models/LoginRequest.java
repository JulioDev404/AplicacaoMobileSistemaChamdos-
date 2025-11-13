package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("email")
    private String login;
    @SerializedName("senha")
    private String senha;

    public LoginRequest(String login,String senha){
        this.login = login;
        this.senha = senha;
    }
}
