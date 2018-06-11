package pucminas.com.br.luz_agua.data;

import pucminas.com.br.luz_agua.models.Holder;

public class HolderData {
    private String nome;
    private String doc;

    public HolderData(String nome, String doc) {
        this.nome = nome;
        this.doc = doc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDoc() {
        return doc;
    }

    public void setdoc(String doc) {
        this.doc = doc;
    }
}
