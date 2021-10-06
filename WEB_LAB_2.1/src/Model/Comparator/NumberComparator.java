package Model.Comparator;

import Model.Classes.InsuranceContracts;

import java.util.Comparator;

public class NumberComparator implements Comparator<InsuranceContracts> {

    public int compare(InsuranceContracts arg0, InsuranceContracts arg1) {
        return Double.compare(arg0.getNumberOfConclusion(), arg1.getNumberOfConclusion());
    }
}
