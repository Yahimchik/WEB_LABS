package Model.Comparator;

import Model.Classes.InsuranceContracts;

import java.util.Comparator;

public class ConclusionDateComparator implements Comparator<InsuranceContracts> {
    public int compare(InsuranceContracts o1, InsuranceContracts o2) {
        return Integer.compare(o1.getDateOfConclusion(), o2.getDateOfConclusion());
    }
}
