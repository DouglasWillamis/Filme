package com.example.filme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bean.Filme;

public class DetalheActivity extends AppCompatActivity {
    private Filme filme;
    private TextView titulo;
    private TextView descricao;
    private ImageView cartaz;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhe);
        titulo = findViewById(R.id.tvDetalheTitulo);
        descricao = findViewById(R.id.tvDescricao);
        cartaz = findViewById(R.id.ivCapa);

        filme = (Filme) getIntent().getSerializableExtra(Filme.class.getSimpleName());
        getSupportActionBar().setTitle("Detalhe de " + filme.getTitulo());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titulo.setText(filme.getTitulo());
        descricao.setText(filme.getDescricao());
        Picasso.get().load(filme.getCapa()).into(cartaz);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
