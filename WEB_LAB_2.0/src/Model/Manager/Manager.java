package Model.Manager;

import Model.Comparator.*;
import Model.Classes.Contracts;

import java.util.ArrayList;

public class Manager {

    public ArrayList<Contracts> sortByConcludedContracts(ArrayList<Contracts> arrayList) {
        ConcludedContractComparator comparator = new ConcludedContractComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<Contracts> sortByConclusionDate(ArrayList<Contracts> arrayList) {
        ConclusionDateComparator comparator = new ConclusionDateComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<Contracts> sortByExpirationDate(ArrayList<Contracts> arrayList) {
        ExpirationDateComparator comparator = new ExpirationDateComparator();
        arrayList.sort(comparator);
        return arrayList;
    }


    public ArrayList<Contracts> sortByContractName(ArrayList<Contracts> arrayList) {
        NameComparator comparator = new NameComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<Contracts> sortByNumber(ArrayList<Contracts> arrayList) {
        NumberComparator comparator = new NumberComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public void printArray(ArrayList<Contracts> arrayList) {
        for (Contracts i : arrayList) {
            i.review();
        }
    }
}
