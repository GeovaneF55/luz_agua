package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Bill;
import pucminas.com.br.luz_agua.models.ElectricityBill;
import pucminas.com.br.luz_agua.models.WaterBill;

public class ElectricityBillFactory extends BillAbstractFactory {
    @Override
    public Bill createAccount(String mes, String ano, double leituraAnterior, double leituraAtual) {
        return new ElectricityBill(mes, ano, leituraAnterior, leituraAtual);
    }
}
