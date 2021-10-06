package Model.Factory;

import Model.Classes.Contracts;
import Model.Classes.LegalEntity;

public class IndividualEntityFactory implements Factory {

    public Contracts createContracts() {
        return new LegalEntity("Contract for you",
                2017,
                2064,
                1, "OAO Builder",
                "Egor Aleksandrovich", false);
    }
}
