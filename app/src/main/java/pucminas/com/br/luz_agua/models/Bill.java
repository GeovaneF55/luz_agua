package pucminas.com.br.luz_agua.models;

public abstract class Bill {
    protected String mes;
    protected String ano;
    protected double leituraAnterior;
    protected double leituraAtual;

    public Bill() { }

    public Bill(String mes, String ano, double leituraAnterior, double leituraAtual) {
        this.mes = mes;
        this.ano = ano;
        this.leituraAnterior = leituraAnterior;
        this.leituraAtual = leituraAtual;
    }

    public abstract double calcularValor();

    public String getDate() {
        return mes + "/" + ano;
    }
    public double getLeituraAnterior() {
        return leituraAnterior;
    }
    public double getLeituraAtual() {
        return leituraAtual;
    }
}
