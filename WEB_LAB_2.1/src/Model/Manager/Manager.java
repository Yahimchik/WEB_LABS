package Model.Manager;

import Model.Comparator.*;
import Model.Classes.InsuranceContracts;

import java.util.ArrayList;

public class Manager {

    public ArrayList<InsuranceContracts> sortByConcludedContracts(ArrayList<InsuranceContracts> arrayList) {
        ConcludedContractComparator comparator = new ConcludedContractComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<InsuranceContracts> sortByConclusionDate(ArrayList<InsuranceContracts> arrayList) {
        ConclusionDateComparator comparator = new ConclusionDateComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<InsuranceContracts> sortByExpirationDate(ArrayList<InsuranceContracts> arrayList) {
        ExpirationDateComparator comparator = new ExpirationDateComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<InsuranceContracts> sortByContractName(ArrayList<InsuranceContracts> arrayList) {
        NameComparator comparator = new NameComparator();
        arrayList.sort(comparator);
        return arrayList;
    }

    public ArrayList<InsuranceContracts> sortByNumber(ArrayList<InsuranceContracts> arrayList) {
        NumberComparator comparator = new NumberComparator();
        arrayList.sort(comparator);
        return arrayList;
    }
}
