package Model.Comparator;

import Model.Classes.Contracts;

import java.util.Comparator;

public class NameComparator implements Comparator<Contracts> {
    public int compare(Contracts left, Contracts right) {
        return left.getName().compareTo(right.getName());
    }
}
