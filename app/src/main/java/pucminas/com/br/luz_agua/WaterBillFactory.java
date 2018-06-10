package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Bill;
import pucminas.com.br.luz_agua.models.WaterBill;

public class WaterBillFactory extends BillAbstractFactory {
    @Override
    public Bill createAccount(String mes, String ano, double leituraAnterior, double leituraAtual) {
        return new WaterBill(mes, ano, leituraAnterior, leituraAtual);
    }
}
