package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("sucesso")
    private boolean sucesso;

    @SerializedName("usuario")
    private Usuario usuario;

    @SerializedName("mensagem")
    private String mensagem;

    public boolean isSucesso() {
        return sucesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getMensagem() {
        return mensagem;
    }
}
