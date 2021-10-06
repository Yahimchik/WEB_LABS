package Model.Comparator;

import Model.Classes.Contracts;

import java.util.Comparator;

public class ConclusionDateComparator implements Comparator<Contracts> {
    public int compare(Contracts o1, Contracts o2) {
        return Integer.compare(o1.getDateOfConclusion(), o2.getDateOfConclusion());
    }
}
