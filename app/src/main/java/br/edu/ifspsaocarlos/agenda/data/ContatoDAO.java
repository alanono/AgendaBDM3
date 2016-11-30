package br.edu.ifspsaocarlos.agenda.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import io.realm.Realm;
import io.realm.RealmQuery;

import java.util.ArrayList;
import java.util.List;


public class ContatoDAO {
    private Realm realm;

    public ContatoDAO(Realm realm) {
        this.realm = realm;
    }

    public  List<Contato> buscaContato(String nomeOuFone)
    {
        RealmQuery<Contato> query = realm.where(Contato.class);

        if(nomeOuFone != null && !nomeOuFone.isEmpty())
            query.contains("nome", nomeOuFone).or().contains("fone", nomeOuFone);

        List<Contato> contatos = query.findAll();
        return contatos;
    }

    public Contato buscaContatoPorId(String id)
    {
        RealmQuery<Contato> query = realm.where(Contato.class);
        return query.contains("id", id).findFirst();
    }

    public void atualizaContato(Contato c, String name, String fone, String fone2, String email, String aniv) {

        realm.beginTransaction();
        c.setNome(name);
        c.setFone(fone);
        c.setFone2(fone2);
        c.setEmail(email);
        c.setAniv(aniv);
        realm.commitTransaction();
    }


    public void insereContato(Contato c) {
        realm.beginTransaction();
        realm.copyToRealm(c);
        realm.commitTransaction();
    }

    public void apagaContato(Contato c){
        realm.beginTransaction();
        c.deleteFromRealm();
        realm.commitTransaction();
    }

}
