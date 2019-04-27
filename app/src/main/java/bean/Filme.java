package bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Filme implements Serializable {
    public String titulo;
    public  String capa;
    public String descricao;

    public Filme(JSONObject jsonObject){
        try {
            this.titulo = jsonObject.getString("titulo");
            this.capa = jsonObject.getString("capa");
            this.descricao = jsonObject.getString("descricao");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
