package br.ufop.rafael.minhacarteirinha;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rafael on 09/05/15.
 */
public class TelaPrimeiraVez extends ActionBarActivity {
    
    private Button btEvia;
    private EditText etNome;
    private Spinner spValores;
    private Float valorSel;

    private static final String NOME = "minhaCarteirinha";
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(NOME, MODE_PRIVATE);
        setContentView(R.layout.tela_primeira_execucao);
        addButtonListeners();
        preencheSpinner();
    }

    private void addButtonListeners() {
        btEvia =(Button) findViewById(R.id.btEnvia);
        etNome = (EditText) findViewById(R.id.etNome);
        spValores = (Spinner) findViewById(R.id.tela_add_spValores);

        btEvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();

                if(nome.equals("") == false) {


                    Log.d("nome aqui", nome);

                    prefs.edit().putString("alunoNome", nome).commit();
                    prefs.edit().putFloat("alunoSaldo", valorSel).commit();
                    finish();
                }
                else{
                    Toast.makeText(TelaPrimeiraVez.this,"Nome não pode estar em branco", Toast.LENGTH_SHORT).show();
                }

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
        if(id == R.id.action_add_rmv_icSaldo || id == R.id.action_add_rmv_opsaldo){
            Intent it = new Intent(TelaPrimeiraVez.this, TelaSaldo.class);
            startActivity(it);
            this.finish();
        }
        if(id == R.id.action_opcardapio || id == R.id.action_iccardapio){
            Intent it = new Intent(TelaPrimeiraVez.this, TelaCardapio.class);
            startActivity(it);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencheSpinner() {

        ArrayList<Float> valores = new ArrayList<Float>();
        Float valor = -4.0f;
        //Gera array para popular o adapter
        for(int i = 0 ; i < 51; i++){

            valores.add(valor);

            valor += 2.0f;
        }

        ArrayAdapter<Float> adapter = new ArrayAdapter<Float>(this,android.R.layout.simple_spinner_item,valores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spValores.setAdapter(adapter);

        spValores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int posicao, long id) {
                valorSel = Float.parseFloat(parent.getItemAtPosition(posicao).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
