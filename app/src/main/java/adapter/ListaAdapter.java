package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filme.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.Filme;

public class ListaAdapter extends ArrayAdapter<Filme> {
    public ListaAdapter(@NonNull Context context, List<Filme> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.adapter_lista, parent, false);
        Filme filme = getItem(position);

        ImageView capa = root.findViewById(R.id.ivCapaAdapter);
        Picasso.get().load(filme.getCapa()).into(capa);

        TextView titulo = root.findViewById(R.id.tvTitulo);
        titulo.setText(filme.getTitulo());

        return root;
    }
}
