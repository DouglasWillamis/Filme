package com.example.filme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import adapter.ListaAdapter;
import bean.Filme;
import mobi.stos.httplib.HttpAsync;
import mobi.stos.httplib.inter.FutureCallback;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ListaAdapter listaAdapter;
    ArrayList<Filme> filmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmes = new ArrayList<>();
        listView = findViewById(R.id.lista);


        try {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            final HttpAsync httpAsync = new HttpAsync(new URL("http://aula.stos.mobi/filmes"));
            httpAsync.get(new FutureCallback() {
                @Override
                public void onBeforeExecute() {
                    progressDialog.setTitle("Carregando");
                    progressDialog.setMessage("Carregando filmes...");
                    progressDialog.show();
                }

                @Override
                public void onAfterExecute() {
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onSuccess(int responseCode, Object object) {
                    if (responseCode == 200){
                        JSONArray jsonArray = (JSONArray) object;
                        for (int i = 0; i < jsonArray.length(); i++){
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Filme filme = new Filme(jsonObject);
                                filmes.add(filme);
                                listaAdapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Exception exception) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        listaAdapter = new ListaAdapter(this, filmes);
        listView.setAdapter(listaAdapter);
        listaAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DetalheActivity.class);
                intent.putExtra(Filme.class.getSimpleName(), filme);
                startActivity(intent);
            }
        });

    }
}
