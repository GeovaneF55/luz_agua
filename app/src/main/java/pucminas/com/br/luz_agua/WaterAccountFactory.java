package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Account;
import pucminas.com.br.luz_agua.models.WaterAccount;

public class WaterAccountFactory extends AccountAbstractFactory {
    @Override
    public Account createAccount() {
        return new WaterAccount();
    }
}
