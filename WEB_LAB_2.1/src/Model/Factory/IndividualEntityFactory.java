package Model.Factory;

import Model.Classes.InsuranceContracts;
import Model.Classes.LegalEntity;

public class IndividualEntityFactory implements Factory {

    public InsuranceContracts createContracts() {
        return new LegalEntity("Contract for you",
                2017,
                2064,
                1, "OAO Builder",
                "Egor Aleksandrovich", false);
    }
}
