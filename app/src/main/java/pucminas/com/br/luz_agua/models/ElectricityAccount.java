package pucminas.com.br.luz_agua.models;

public class ElectricityAccount extends Account {
    @Override
    public double calcularValor() {
        // TODO: Metodo de calcular valor
        return this.leituraAtual;
    }
}
