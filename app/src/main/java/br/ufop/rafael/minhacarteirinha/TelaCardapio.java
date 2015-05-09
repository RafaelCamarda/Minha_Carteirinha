package br.ufop.rafael.minhacarteirinha;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by rafael on 09/05/15.
 */
public class TelaCardapio extends ActionBarActivity implements View.OnClickListener {

    private Button btVerCardapio;
    private Button btAvalia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cardapio);
        addButtonListeners();

    }

    private void addButtonListeners() {

        btVerCardapio = (Button) findViewById(R.id.btVerCardapio);
        btVerCardapio.setOnClickListener(this);

        btAvalia = (Button) findViewById(R.id.btEnviarAvaliacao);
        btAvalia.setOnClickListener(this);
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
            Intent it = new Intent(TelaCardapio.this, TelaSaldo.class);
            startActivity(it);
            this.finish();
        }
        if(id == R.id.action_opcardapio || id == R.id.action_iccardapio){
            Intent it = new Intent(TelaCardapio.this, TelaCardapio.class);
            startActivity(it);
            this.finish();
        }
        if(id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btVerCardapio){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ufop.br/index.php?option=com_content&task=view&id=16197&Itemid=298"));
            startActivity(browserIntent);
        }
        if(v.getId() == R.id.btEnviarAvaliacao){
            Log.d("entrei", "aqui");
            Toast.makeText(TelaCardapio.this, "Função não implementada", Toast.LENGTH_SHORT).show();
        }

    }
}
