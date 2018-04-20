package br.usjt.desvweb.servicedeskcco.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Filipe Roque Charamello R.A 816118349 on 19/04/18.
 */

public class ChamadoNetwork {

    public static ArrayList<Chamado> buscarChamados(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ArrayList<Chamado> chamados = new ArrayList<>();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            JSONArray lista = new JSONArray(json);
            for(int i = 0; i < lista.length(); i++){
                JSONObject item = (JSONObject) lista.get(i);
                Chamado chamado = new Chamado();
                chamado.setNumero(item.getInt("numero"));
                chamado.setDescricao(item.getString("nome"));
                chamado.setStatus(item.getString("status"));
                String sDataAbertura = item.getString("dataAbertura");
                String sDataFechamento = item.getString("dataFechamento");
                try {
                    chamado.setDataAbertura((Date) df.parse(sDataAbertura));
                } catch(ParseException e){
                    chamado.setDataAbertura(null);
                }
                try {
                    chamado.setDataFechamento((Date) df.parse(sDataFechamento));
                } catch(ParseException e){
                    chamado.setDataFechamento(null);
                }

                JSONObject filaItem = item.getJSONObject("fila");
                Fila fila = new Fila();
                fila.setId(filaItem.getInt("id"));
                fila.setNome(filaItem.getString("nome"));
                fila.setFigura(filaItem.getString("figura"));
                chamado.setFila(fila);
                chamados.add(chamado);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chamados;
    }
}
