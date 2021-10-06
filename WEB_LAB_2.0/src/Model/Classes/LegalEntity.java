package Model.Classes;

import java.util.Objects;

public class LegalEntity extends Contracts {
    private String companyName;
    private String nameOfGeneralDirector;

    public LegalEntity(String name, int dateOfConclusion, int expirationDateOfTheContract,
                       int numberOfConclusion, String companyName, String nameOfGeneralDirector, boolean included) {

        super(name, dateOfConclusion, expirationDateOfTheContract, numberOfConclusion, included);
        this.companyName = companyName;
        this.nameOfGeneralDirector = nameOfGeneralDirector;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNameOfGeneralDirector() {
        return nameOfGeneralDirector;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNameOfGeneralDirector(String nameOfGeneralDirector) {
        this.nameOfGeneralDirector = nameOfGeneralDirector;
    }

    public void review() {
        String str = String.format("""

                        Company Name: %s
                        Name of general director: %s
                        Contract name: %s
                        Date of conclusion: %s
                        Expiration date of the contract: %s
                        Number of conclusion: %s
                        Concluded: %s
                        """,
                this.companyName, this.nameOfGeneralDirector, this.getName(),
                this.getDateOfConclusion(), this.getExpirationDateOfTheContract(),
                this.getNumberOfConclusion(), this.isConcluded() ? "true" : "false");
        System.out.println(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contracts contracts = (Contracts) obj;
        LegalEntity legalEntity = (LegalEntity) obj;

        return contracts.equals(this) && Objects.equals(companyName, legalEntity.companyName) &&
                Objects.equals(nameOfGeneralDirector, legalEntity.nameOfGeneralDirector);
    }

    public String toString() {
        String stringFormatSuper = super.toString();
        return String.format("""
                        %s
                        Company name: %s
                        Name of general director: %s""",
                stringFormatSuper, this.companyName,
                this.nameOfGeneralDirector);
    }

    public int hashCode() {
        int hashFromSuper = super.hashCode();
        return 17 * (getNumberOfConclusion() * 21 + hashFromSuper);
    }
}
