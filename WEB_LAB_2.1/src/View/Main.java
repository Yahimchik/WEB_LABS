package View;

import Model.Classes.InsuranceContracts;
import Model.Factory.IndividualEntityFactory;
import Model.Factory.Factory;
import Model.Factory.LegalEntityFactory;
import Model.Manager.Manager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;

import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {

        Label selectedLbl = new Label();
        Manager manager = new Manager();

        Factory person = new LegalEntityFactory();
        Factory company = new IndividualEntityFactory();

        ArrayList<InsuranceContracts> contracts = new ArrayList<>();

        contracts.add(person.createContracts());
        contracts.add(company.createContracts());

        RadioButton button1 = new RadioButton("Show all contracts");
        RadioButton button2 = new RadioButton("Sort contracts by concluded contract");
        RadioButton button3 = new RadioButton("Sort contracts by conclusion date");
        RadioButton button4 = new RadioButton("Sort contracts by expiration date");
        RadioButton button5 = new RadioButton("Sort contracts by contract name");
        RadioButton button6 = new RadioButton("Sort contracts by number");

        ToggleGroup group = new ToggleGroup();

        button1.setToggleGroup(group);
        button2.setToggleGroup(group);
        button3.setToggleGroup(group);
        button4.setToggleGroup(group);
        button5.setToggleGroup(group);
        button6.setToggleGroup(group);

        button1.setOnAction(actionEvent -> selectedLbl.setText(contracts.toString()));

        button2.setOnAction(event -> selectedLbl.setText(manager.sortByConcludedContracts(contracts).toString()));

        button3.setOnAction(event -> selectedLbl.setText(manager.sortByConclusionDate(contracts).toString()));

        button4.setOnAction(event -> selectedLbl.setText(manager.sortByExpirationDate(contracts).toString()));

        button5.setOnAction(event -> selectedLbl.setText(manager.sortByContractName(contracts).toString()));

        button6.setOnAction(event -> selectedLbl.setText(manager.sortByNumber(contracts).toString()));

        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
        root.getChildren().addAll(button1, button2, button3, button4, button5,
                button6, selectedLbl);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 450, 420);

        stage.setScene(scene);
        stage.setTitle("Insurance contracts");
        stage.show();
    }
}
