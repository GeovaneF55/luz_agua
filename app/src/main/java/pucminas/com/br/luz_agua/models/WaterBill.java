package pucminas.com.br.luz_agua.models;

public class WaterBill extends Bill {
    public WaterBill(String mes, String ano, double leituraAnterior, double leituraAtual) {
        super(mes, ano, leituraAnterior, leituraAtual);
    }

    @Override
    public double calcularValor() {
        // TODO: Metodo de calcular valor
        return leituraAtual;
    }
}
