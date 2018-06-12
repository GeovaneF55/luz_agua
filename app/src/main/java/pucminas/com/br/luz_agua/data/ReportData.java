package pucminas.com.br.luz_agua.data;

public class ReportData {

    private String conta;
    private String data;
    private double consumo;
    private double consumo_anterior;
    private double valor;

    public ReportData(String conta, String data, double consumo, double consumo_anterior, double valor) {
        this.conta = conta;
        this.data = data;
        this.consumo = consumo;
        this.consumo_anterior = consumo_anterior;
        this.valor = valor;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getConsumo_anterior() {
        return consumo_anterior;
    }

    public void setConsumo_anterior(double consumo_anterior) {
        this.consumo_anterior = consumo_anterior;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
