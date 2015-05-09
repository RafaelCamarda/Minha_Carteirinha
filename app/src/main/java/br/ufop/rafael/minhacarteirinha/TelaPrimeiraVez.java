package br.ufop.rafael.minhacarteirinha;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rafael on 09/05/15.
 */
public class TelaPrimeiraVez extends ActionBarActivity {
    
    private Button btEvia;
    private EditText etSaldo;
    private EditText etNome;

    private static final String NOME = "minhaCarteirinha";
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(NOME, MODE_PRIVATE);
        setContentView(R.layout.tela_primeira_execucao);
        addButtonListeners();
    }

    private void addButtonListeners() {
        btEvia =(Button) findViewById(R.id.btEnvia);
        etSaldo = (EditText) findViewById(R.id.etSaldo);
        etNome = (EditText) findViewById(R.id.etNome);

        btEvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                Float saldo = Float.parseFloat(etSaldo.getText().toString());

                Log.d("nome aqui",nome);

                prefs.edit().putString("alunoNome",nome).commit();
                prefs.edit().putFloat("alunoSaldo", saldo).commit();
                finish();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
