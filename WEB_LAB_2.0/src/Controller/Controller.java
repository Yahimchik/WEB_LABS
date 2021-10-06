package Controller;

import Model.Manager.Manager;
import Model.Classes.Contracts;
import Model.Factory.IndividualEntityFactory;
import Model.Factory.Factory;
import Model.Factory.LegalEntityFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public void show() {

        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();

        Factory person = new LegalEntityFactory();
        Factory company = new IndividualEntityFactory();

        ArrayList<Contracts> contracts = new ArrayList<>();
        ArrayList<Contracts> arrayList;

        contracts.add(person.createContracts());
        contracts.add(company.createContracts());

        printText("""
                __________________________________________
                |1. Exit.                                |
                |2. Show all contracts.                  |
                |3. Sort contracts by concluded contract.|
                |4. Sort contracts by conclusion date.   |
                |5. Sort contracts by expiration date.   |
                |6. Sort contracts by contract name.     |
                |7. Sort contracts by number.            |
                |8. Chose parameters.                    |
                ––––––––––––––––––––––––––––––––––––––––––
                """);

        while (true) {
            printText("Enter number:\040");
            int choice = scanner.nextInt();

            if (choice == 1) {
                printText("Exit...");
                break;
            }

            if (choice < 1 || choice > 8) {
                printText("You enter incorrect menu function. Please, try again!\n");
            }

            switch (choice) {
                case 2:
                    printText("All contracts:\n");
                    for (Contracts allContracts : contracts) {
                        allContracts.review();
                    }
                    break;
                case 3:
                    printText("\nSort by concluded contract:\n");
                    arrayList = manager.sortByConcludedContracts(contracts);
                    manager.printArray(arrayList);
                    break;
                case 4:
                    printText("\nSort by conclusion date:\n");
                    arrayList = manager.sortByConclusionDate(contracts);
                    manager.printArray(arrayList);
                    break;
                case 5:
                    printText("\nSort by expiration date:\n");
                    arrayList = manager.sortByExpirationDate(contracts);
                    manager.printArray(arrayList);
                    break;
                case 6:
                    printText("\nSort by contract name: ");
                    arrayList = manager.sortByContractName(contracts);
                    manager.printArray(arrayList);
                    break;
                case 7:
                    printText("\nSort by contract number:\n");
                    arrayList = manager.sortByNumber(contracts);
                    manager.printArray(arrayList);
                    break;
                case 8:
                    printText("\nShow info by chosen parameters:\n");
                    printText("Enter numbers of parameters: ");

                    String str = scanner.next();
                    String[] strings = str.split("");
                    int[] numbers = new int[str.length()];

                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = Integer.parseInt(strings[i]);
                    }
                    for (int number : numbers) {
                        switch (number) {
                            case 1:
                                System.out.println("\nCompany name: "
                                        + contracts.get(0).getName());
                                System.out.println("Company name: "
                                        + contracts.get(1).getName() + "\n");
                                break;
                            case 2:
                                System.out.println("Conclusion date: "
                                        + contracts.get(0).getDateOfConclusion());
                                System.out.println("Conclusion date: "
                                        + contracts.get(1).getDateOfConclusion() + "\n");
                                break;
                            case 3:
                                System.out.println("Expiration date: "
                                        + contracts.get(0).getExpirationDateOfTheContract());
                                System.out.println("Expiration date: "
                                        + contracts.get(1).getExpirationDateOfTheContract() + "\n");
                                break;
                            case 4:
                                System.out.println("Number of conclusion: "
                                        + contracts.get(0).getNumberOfConclusion());
                                System.out.println("Number of conclusion: "
                                        + contracts.get(1).getNumberOfConclusion() + "\n");
                                break;
                            case 5:
                                System.out.println("Concluded: "
                                        + contracts.get(0).isConcluded());
                                System.out.println("Concluded: "
                                        + contracts.get(1).isConcluded() + "\n");
                                break;
                            default:
                                break;
                        }
                    }
                default:
                    break;
            }
        }
    }

    public static void printText(String text) {
        System.out.print(text);
    }
}
