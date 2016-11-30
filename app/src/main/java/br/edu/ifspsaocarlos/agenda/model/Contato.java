package br.edu.ifspsaocarlos.agenda.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contato extends RealmObject implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final String CONTATO_ID = "id";
    @PrimaryKey
    private String id;
    private String nome;
    private String fone;
    private String fone2;
    private String email;
    private String aniv;

    public Contato() {
        id = UUID.randomUUID().toString();
    }

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
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public String getFone2() {
        return fone2;
    }
    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAniv() {
        return this.aniv;
    }
    public void setAniv(String aniv) {
        this.aniv = aniv;
    }
}

