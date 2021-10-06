package Model.Comparator;

import Model.Classes.Contracts;

import java.util.Comparator;

public class NumberComparator implements Comparator<Contracts> {

    public int compare(Contracts arg0, Contracts arg1) {
        return Double.compare(arg0.getNumberOfConclusion(), arg1.getNumberOfConclusion());
    }
}
