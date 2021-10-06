package Model.Factory;

import Model.Classes.Contracts;
import Model.Classes.IndividualEntity;

public class LegalEntityFactory implements Factory {

    public Contracts createContracts() {
        return new IndividualEntity("Contract2", 2021,
                2025, 2,
                "Egor", "Yahimovich",
                "Aleksandrovich", true);
    }
}
