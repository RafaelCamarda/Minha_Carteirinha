package br.ufop.rafael.minhacarteirinha;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private static final String NOME = "minhaCarteirinha";
    SharedPreferences prefs = null;

    private TextView tvSaldo;
    private TextView tvNome;
    private Button btSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(NOME, MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        setPreferences();
        addButtonListeners();

        //Detecta a primeira execução
        if (prefs.getBoolean("firstrun", true)) {
            //Inicia a tela de cadastro inicial
            Intent it = new Intent(MainActivity.this, TelaPrimeiraVez.class);
            startActivity(it);

            //Seta a primeira execução como falsa
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    private void setPreferences() {
        tvSaldo = (TextView) findViewById(R.id.tvSaldo);
        tvNome = (TextView) findViewById(R.id.tvNome);

        tvNome.setText(prefs.getString("alunoNome","Nome"));
        tvSaldo.setText(Float.toString(prefs.getFloat("alunoSaldo", (float) 0.0)));
    }

    private void addButtonListeners() {
        tvSaldo = (TextView) findViewById(R.id.tvSaldo);
        tvNome = (TextView) findViewById(R.id.tvNome);

        btSaldo = (Button) findViewById(R.id.btSaldo);

        btSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float saldo = prefs.getFloat("alunoSaldo",0.0f);

                saldo -= 2.0f;

                prefs.edit().putFloat("alunoSaldo", saldo).commit();
                tvSaldo.setText(Float.toString(saldo));

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

    @Override
    public void onResume(){
        super.onResume();
        setPreferences();
    }

}
