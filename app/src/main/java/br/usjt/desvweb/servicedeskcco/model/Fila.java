package br.usjt.desvweb.servicedeskcco.model;

import java.io.Serializable;

/**
 * Created by Filipe Roque Charamello R.A 816118349 on 19/04/2018.
 */

public class Fila implements Serializable {
    private int id;
    private String nome, figura;

    public Fila() {
    }

    public Fila(int id, String nome, String figura) {
        this.id = id;
        this.nome = nome;
        this.figura = figura;
    }

    @Override
    public String toString() {
        return "Fila{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", figura='" + figura + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
