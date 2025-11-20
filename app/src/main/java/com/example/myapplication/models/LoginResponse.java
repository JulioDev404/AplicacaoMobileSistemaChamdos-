package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("sucesso")
    private boolean sucesso;

    @SerializedName("usuario")
    private Usuario usuario;
    @SerializedName("token")
    private String Token;
    @SerializedName("mensagem")
    private String mensagem;

    public boolean isSucesso() {
        return sucesso;
    }

    public String getToken() {
        return Token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getMensagem() {
        return mensagem;
    }
}
