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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rafael on 09/05/15.
 */
public class TelaSaldo extends ActionBarActivity {

    private Button btOK;
    private RadioButton rbADD;
    private RadioButton rbRMV;
    private Spinner spValores;
    private Float valorSel;

    SharedPreferences prefs = null;
    private static final String NOME = "minhaCarteirinha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(NOME, MODE_PRIVATE);
        setContentView(R.layout.tela_add_rmv_saldo);
        addButtonListeners();
        preencheSpinner();




    }

    private void preencheSpinner() {

        ArrayList<Float> valores = new ArrayList<Float>();
        Float valor = 0.0f;
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

    private void addButtonListeners() {
        btOK =(Button) findViewById(R.id.btOK);
        rbADD = (RadioButton) findViewById(R.id.rbADD);
        rbRMV = (RadioButton) findViewById(R.id.rbRMV);
        spValores = (Spinner) findViewById(R.id.tela_add_spValores);

        rbADD.setChecked(true);

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Float saldo = prefs.getFloat("alunoSaldo",0.0f);

                if(rbADD.isChecked() == true){
                    saldo += valorSel;
                }
                else if(rbRMV.isChecked() == true){
                    if(saldo > -4.0f){
                        saldo -= valorSel;
                        prefs.edit().putFloat("alunoSaldo", saldo).commit();
                        finish();
                    }
                    else
                        Toast.makeText(TelaSaldo.this, "Saldo não pode ser menor do que 4, recarregue!!", Toast.LENGTH_SHORT).show();
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

        }
        if(id == R.id.action_add_rmv_icSaldo || id == R.id.action_add_rmv_opsaldo){
            Intent it = new Intent(TelaSaldo.this, TelaSaldo.class);
            startActivity(it);
            this.finish();
        }
        if(id == R.id.action_opcardapio || id == R.id.action_iccardapio){
            Intent it = new Intent(TelaSaldo.this, TelaCardapio.class);
            startActivity(it);
            this.finish();
        }
        if(id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
