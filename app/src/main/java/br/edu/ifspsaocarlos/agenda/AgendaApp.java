package br.edu.ifspsaocarlos.agenda;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Alan on 29/11/2016.
 */

public class AgendaApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
    }
}
