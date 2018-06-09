package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Account;
import pucminas.com.br.luz_agua.models.ElectricityAccount;

public class ElectricityAccountFactory extends AccountAbstractFactory {
    @Override
    public Account createAccount() {
        return new ElectricityAccount();
    }
}
