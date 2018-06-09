package pucminas.com.br.luz_agua.models;

public class Individual extends Holder {
    private String firstName;
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
