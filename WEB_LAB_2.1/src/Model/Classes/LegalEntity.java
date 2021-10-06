package Model.Classes;

import java.util.Objects;

public class LegalEntity extends InsuranceContracts {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InsuranceContracts insuranceContracts = (InsuranceContracts) obj;
        LegalEntity legalEntity = (LegalEntity) obj;

        return insuranceContracts.equals(this) && Objects.equals(companyName, legalEntity.companyName) &&
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
