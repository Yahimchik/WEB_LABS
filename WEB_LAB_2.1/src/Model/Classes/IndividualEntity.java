package Model.Classes;

import java.util.Objects;

public class IndividualEntity extends InsuranceContracts {
    private String firstName;
    private String surname;
    private String patronymic;

    public IndividualEntity(String name, int dateOfConclusion, int expirationDateOfTheContract, int numberOfConclusion,
                            String firstName, String surname, String patronymic, boolean concluded) {

        super(name, dateOfConclusion, expirationDateOfTheContract, numberOfConclusion, concluded);
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InsuranceContracts insuranceContracts = (InsuranceContracts) obj;
        IndividualEntity individualEntity = (IndividualEntity) obj;
        return insuranceContracts.equals(this) &&
                Objects.equals(firstName, individualEntity.firstName) &&
                Objects.equals(surname, individualEntity.surname) &&
                Objects.equals(patronymic, individualEntity.patronymic);
    }

    public String toString() {
        String stringFromSuper = super.toString();
        return String.format("""
                        %s
                        First name: %s
                        Surname: %s
                        Patronymic: %s""",
                stringFromSuper, this.firstName,
                this.surname, this.patronymic);
    }

    public int hashCode() {
        int hashFromSuper = super.hashCode();
        return 77 * (getNumberOfConclusion() * 17 + hashFromSuper);
    }
}
