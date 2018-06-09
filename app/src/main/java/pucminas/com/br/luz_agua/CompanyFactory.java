package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Company;
import pucminas.com.br.luz_agua.models.Holder;

public class CompanyFactory extends HolderAbstractFactory {

    @Override
    public Holder createHolder() {
        return new Company();
    }
}
