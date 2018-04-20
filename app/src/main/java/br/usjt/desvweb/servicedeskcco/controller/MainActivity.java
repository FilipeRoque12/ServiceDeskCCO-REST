package br.usjt.desvweb.servicedeskcco.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.desvweb.servicedeskcco.R;
import br.usjt.desvweb.servicedeskcco.model.Chamado;
import br.usjt.desvweb.servicedeskcco.model.ChamadoNetwork;

public class MainActivity extends Activity {
    public static final String CHAMADOS = "br.usjt.desvweb.servicedeskcco.chamados";
    private EditText txtFila;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFila = (EditText)findViewById(R.id.buscar_fila);
        contexto = this;
    }

    public void buscarChamados(View view) {
        String fila = txtFila.getText().toString();
        new DownloadJsonChamados().execute("http://192.168.0.105:8080/arqsw_sdesk_a1/rest/chamados/");
    }

    private class DownloadJsonChamados extends AsyncTask<String, Void, ArrayList<Chamado>>{

        @Override
        protected ArrayList<Chamado> doInBackground(String... strings) {
            ArrayList<Chamado> chamados = null;
            try {
                chamados = ChamadoNetwork.buscarChamados(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return chamados;
        }

        protected void onPostExecute(ArrayList<Chamado> chamados){
            Intent intent = new Intent(contexto, ListarChamadosActivity.class);
            intent.putExtra(CHAMADOS, chamados);
            startActivity(intent);
        }
    }

}
