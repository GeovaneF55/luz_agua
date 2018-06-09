package pucminas.com.br.luz_agua;

import pucminas.com.br.luz_agua.models.Holder;
import pucminas.com.br.luz_agua.models.Individual;

public class IndividualFactory extends HolderAbstractFactory {

    @Override
    public Holder createHolder() {
        return new Individual();
    }
}
