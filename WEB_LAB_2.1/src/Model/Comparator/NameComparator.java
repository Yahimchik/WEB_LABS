package Model.Comparator;

import Model.Classes.InsuranceContracts;

import java.util.Comparator;

public class NameComparator implements Comparator<InsuranceContracts> {
    public int compare(InsuranceContracts left, InsuranceContracts right) {
        return left.getName().compareTo(right.getName());
    }
}
