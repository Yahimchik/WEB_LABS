package Model.Comparator;

import Model.Classes.InsuranceContracts;

import java.util.Comparator;

public class ConcludedContractComparator implements Comparator<InsuranceContracts> {

    public int compare(InsuranceContracts o1, InsuranceContracts o2) {
        return Boolean.compare(o1.isConcluded(), o2.isConcluded());
    }
}
