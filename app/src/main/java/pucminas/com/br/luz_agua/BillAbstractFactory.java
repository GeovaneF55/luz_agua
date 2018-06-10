package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Bill;

public abstract class BillAbstractFactory {
    public abstract Bill createAccount(String mes, String ano, double leituraAnterior, double leituraAtual);
}
