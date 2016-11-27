package br.edu.ifspsaocarlos.agenda.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;


public class ContatoDAO {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ContatoDAO(Context context) {
        this.dbHelper=new SQLiteHelper(context);
    }

    public  List<Contato> buscaContato(String nomeOuFone)
    {
        database=dbHelper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        String[] cols=new String[] {SQLiteHelper.KEY_ID,SQLiteHelper.KEY_NAME,
                SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_FONE2, SQLiteHelper.KEY_EMAIL, SQLiteHelper.KEY_ANIV};
        String where=null;
        String[] argWhere=null;

        if (nomeOuFone!=null) {
            where = SQLiteHelper.KEY_NAME + " like ? or " + SQLiteHelper.KEY_FONE + " like ?";
            argWhere = new String[]{nomeOuFone + "%", nomeOuFone + "%"};
        }


        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, where , argWhere,
                null, null, SQLiteHelper.KEY_NAME);



       if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contato contato = new Contato();
                contato.setId(cursor.getInt(0));
                contato.setNome(cursor.getString(1));
                contato.setFone(cursor.getString(2));
                contato.setFone2(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setAniv(cursor.getString(5));
                contatos.add(contato);
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();
        return contatos;
    }

    public void atualizaContato(Contato c) {
        database=dbHelper.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(SQLiteHelper.KEY_NAME, c.getNome());
        updateValues.put(SQLiteHelper.KEY_FONE, c.getFone());
        updateValues.put(SQLiteHelper.KEY_FONE2, c.getFone2());
        updateValues.put(SQLiteHelper.KEY_EMAIL, c.getEmail());
        updateValues.put(SQLiteHelper.KEY_ANIV, c.getAniv());
        database.update(SQLiteHelper.DATABASE_TABLE, updateValues, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        database.close();
    }


    public void insereContato(Contato c) {
        database=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NAME, c.getNome());
        values.put(SQLiteHelper.KEY_FONE, c.getFone());
        values.put(SQLiteHelper.KEY_FONE2, c.getFone2());
        values.put(SQLiteHelper.KEY_EMAIL, c.getEmail());
        values.put(SQLiteHelper.KEY_ANIV, c.getAniv());
        database.insert(SQLiteHelper.DATABASE_TABLE, null, values);
        database.close();
    }

    public void apagaContato(Contato c)
    {
        database=dbHelper.getWritableDatabase();
        database.delete(SQLiteHelper.DATABASE_TABLE, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        database.close();
    }
}
