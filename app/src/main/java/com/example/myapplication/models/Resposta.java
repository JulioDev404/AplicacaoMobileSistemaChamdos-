package com.example.myapplication.models;

public class Resposta {

    private String nomeUsuario;
    private String dataResposta;
    private String comentario;
    // Você pode adicionar um campo para a URL da imagem do avatar aqui
    // private String urlAvatar;

    // Construtor
    public Resposta(String nomeUsuario, String dataResposta, String comentario) {
        this.nomeUsuario = nomeUsuario;
        this.dataResposta = dataResposta;
        this.comentario = comentario;
    }

    // Getters (pressione Alt + Insert para gerar)
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getDataResposta() {
        return dataResposta;
    }

    public String getComentario() {
        return comentario;
    }
}