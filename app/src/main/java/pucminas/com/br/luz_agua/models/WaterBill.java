package pucminas.com.br.luz_agua.models;

public class WaterBill extends Bill {
    public WaterBill(String mes, String ano, double leituraAnterior, double leituraAtual) {
        super(mes, ano, leituraAnterior, leituraAtual);
    }

    public WaterBill() { }

    @Override
    public double calcularValor() {
        return 5.88 * leituraAtual;
    }
}
