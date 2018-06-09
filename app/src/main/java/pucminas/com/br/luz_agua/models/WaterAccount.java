package pucminas.com.br.luz_agua.models;

public class WaterAccount extends Account {
    @Override
    public double calcularValor() {
        // TODO: Metodo de calcular valor
        return leituraAtual;
    }
}
