package pucminas.com.br.luz_agua.models;

public class Report {
    private double consumo_anterior;
    private double consumo;

    public double getConsumo() {
        return consumo;
    }

    public double getConsumo_anterior() {
        return consumo_anterior;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public void setConsumo_anterior(double consumo_anterior) {
        this.consumo_anterior = consumo_anterior;
    }

    public double calcVariacaoAbsoluta() {
        return 0;
    }
    public  double calcPercentual() {
        return 0;
    }
    public double gerarRelatorio() {
        return 0;
    }
    public double emitirRelatorio() {
        return 0;
    }
}
