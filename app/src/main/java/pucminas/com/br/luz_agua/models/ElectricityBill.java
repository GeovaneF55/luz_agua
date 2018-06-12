package pucminas.com.br.luz_agua.models;

public class ElectricityBill extends Bill {
    public ElectricityBill(String mes, String ano, double leituraAnterior, double leituraAtual) {
        super(mes, ano, leituraAnterior, leituraAtual);
    }

    @Override
    public double calcularValor() {
        return 7.57 * leituraAtual;
    }
}
