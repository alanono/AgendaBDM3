package br.edu.ifspsaocarlos.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifspsaocarlos.agenda.data.ContatoDAO;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;


public class DetalheActivity extends AppCompatActivity {
    private Contato c;
    private ContatoDAO cDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("contato"))
        {
            this.c = (Contato) getIntent().getSerializableExtra("contato");
            EditText nameText = (EditText)findViewById(R.id.editText1);
            nameText.setText(c.getNome());
            EditText foneText = (EditText)findViewById(R.id.editText2);
            foneText.setText(c.getFone());
            EditText foneText2 = (EditText)findViewById(R.id.editText3);
            foneText2.setText(c.getFone2());
            EditText emailText = (EditText)findViewById(R.id.editText4);
            emailText.setText(c.getEmail());
            EditText anivText = (EditText)findViewById(R.id.editText5);
            anivText.setText(c.getAniv());
            int pos =c.getNome().indexOf(" ");
            if (pos==-1)
                pos=c.getNome().length();
            setTitle(c.getNome().substring(0,pos));
        }
        cDAO = new ContatoDAO(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        if (!getIntent().hasExtra("contato"))
        {
            MenuItem item = menu.findItem(R.id.delContato);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarContato:
                salvar();
                return true;
            case R.id.delContato:
                apagar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void apagar()
    {
        cDAO.apagaContato(c);
        Intent resultIntent = new Intent();
        setResult(3,resultIntent);
        finish();
    }

    public void salvar()
    {
        String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String fone = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String fone2 = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String email = ((EditText) findViewById(R.id.editText4)).getText().toString();
        String aniv = ((EditText) findViewById(R.id.editText5)).getText().toString();

        if(!isDataAnivValida(aniv)){
            Toast.makeText(this, "Date de aniversário inválida", Toast.LENGTH_LONG).show();
            return;
        }

        if (c==null)
        {
            c = new Contato();
            c.setNome(name);
            c.setFone(fone);
            c.setFone2(fone2);
            c.setEmail(email);
            c.setAniv(aniv);
            cDAO.insereContato(c);

        }
        else
        {
            c.setNome(name);
            c.setFone(fone);
            c.setFone2(fone2);
            c.setEmail(email);
            c.setAniv(aniv);
            cDAO.atualizaContato(c);

        }
        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    private boolean isDataAnivValida(String aniv){
        aniv = aniv.trim();
        if(!aniv.isEmpty()){
            String[] split = aniv.split("/");
            if(split.length != 2){
                return false;
            }
            try{
                int dia = Integer.parseInt(split[0]);
                int mes = Integer.parseInt(split[1]);
                if(dia < 1 || dia > 31 || mes < 1 || mes > 12){
                    return false;
                }

            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }
}

