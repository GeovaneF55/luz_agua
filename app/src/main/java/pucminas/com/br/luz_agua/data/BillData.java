package pucminas.com.br.luz_agua.data;

public class BillData {
    private String conta;
    private String data;
    private String consumo;

    public BillData(String conta, String data, String consumo) {
        this.conta = conta;
        this.data = data;
        this.consumo = consumo;
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
}
