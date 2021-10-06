package Model.Classes;

import java.util.Objects;

public class InsuranceContracts {
    private String name;
    private int dateOfConclusion;
    private int expirationDateOfTheContract;
    private int numberOfConclusion;
    private boolean concluded;

    public InsuranceContracts(String name, int dateOfConclusion, int expirationDateOfTheContract,
                              int numberOfConclusion, boolean concluded) {

        this.name = name;
        this.dateOfConclusion = dateOfConclusion;
        this.expirationDateOfTheContract = expirationDateOfTheContract;
        this.numberOfConclusion = numberOfConclusion;
        this.concluded = concluded;
    }

    public String getName() {
        return name;
    }

    public int getDateOfConclusion() {
        return dateOfConclusion;
    }

    public int getExpirationDateOfTheContract() {
        return expirationDateOfTheContract;
    }

    public int getNumberOfConclusion() {
        return numberOfConclusion;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfConclusion(int dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public void setExpirationDateOfTheContract(int expirationDateOfTheContract) {
        this.expirationDateOfTheContract = expirationDateOfTheContract;
    }

    public void setNumberOfConclusion(int numberOfConclusion) {
        this.numberOfConclusion = numberOfConclusion;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InsuranceContracts insuranceContracts = (InsuranceContracts) obj;
        return Objects.equals(name, insuranceContracts.name) && Objects.equals(dateOfConclusion, insuranceContracts.dateOfConclusion)
                && Objects.equals(expirationDateOfTheContract, insuranceContracts.expirationDateOfTheContract)
                && numberOfConclusion == insuranceContracts.numberOfConclusion;
    }

    public String toString() {
        return String.format("""
                        Name: %s
                        Date of conclusion: %s
                        Expiration date of the contract: %s
                        Number of conclusion: %s""",
                this.name, this.dateOfConclusion,
                this.expirationDateOfTheContract, this.numberOfConclusion);
    }

    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result += 17 * (concluded ? 1 : 0);
        result += 17 * (result + numberOfConclusion);
        return result;
    }
}
