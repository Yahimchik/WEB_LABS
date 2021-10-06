package Model.Comparator;

import Model.Classes.Contracts;

import java.util.Comparator;

public class ConcludedContractComparator implements Comparator<Contracts> {

    public int compare(Contracts o1, Contracts o2) {
        return Boolean.compare(o1.isConcluded(), o2.isConcluded());
    }
}
