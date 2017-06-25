package com.pitagoras.cursoandroid;

/**
 * Created by Joham Nunes on 25/06/2017.
 */

public class Nome {

    @com.google.gson.annotations.SerializedName("id")
    private String id;

    @com.google.gson.annotations.SerializedName("nome")
    private String nome;

    @com.google.gson.annotations.SerializedName("sobrenome")
    private String sobrenome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
