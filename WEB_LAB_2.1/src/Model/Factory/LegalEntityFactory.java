package Model.Factory;

import Model.Classes.InsuranceContracts;
import Model.Classes.IndividualEntity;

public class LegalEntityFactory implements Factory {

    public InsuranceContracts createContracts() {
        return new IndividualEntity("Contract2", 2021,
                2025, 2,
                "Egor", "Yahimovich",
                "Aleksandrovich", true);
    }
}
