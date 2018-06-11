package pucminas.com.br.luz_agua.models;

public class Individual extends Holder {
    private String CPF;
    private String firstName;
    private String lastName;

    public String fullName() {
        return firstName + " " + lastName;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
