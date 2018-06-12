package pucminas.com.br.luz_agua.data;

public class ReportData {

    private String conta;
    private String data;
    private String consumo;
    private String consumo_anterior;
    private String valor;

    public ReportData(String conta, String data, String consumo, String consumo_anterior,String valor) {
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

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
