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

    public String date() {
        return mes + "/" + ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public double getLeituraAnterior() {
        return leituraAnterior;
    }

    public void setLeituraAnterior(double leituraAnterior) {
        this.leituraAnterior = leituraAnterior;
    }

    public double getLeituraAtual() {
        return leituraAtual;
    }

    public void setLeituraAtual(double leituraAtual) {
        this.leituraAtual = leituraAtual;
    }
}
