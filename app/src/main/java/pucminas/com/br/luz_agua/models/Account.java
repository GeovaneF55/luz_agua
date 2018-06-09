package pucminas.com.br.luz_agua.models;

public abstract class Account {
    protected String mes;
    protected String ano;
    protected Double leituraAnterior;
    protected Double leituraAtual;

    public abstract double calcularValor();

    public String getDate() {
        return mes + "/" + ano;
    }

    public Double getLeituraAnterior() {
        return leituraAnterior;
    }

    public Double getLeituraAtual() {
        return leituraAtual;
    }
}
