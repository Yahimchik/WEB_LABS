package controller;

import com.sun.tools.javac.Main;
import model.Bank;
import model.Cashier;
import model.Customer;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Controller {
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void test() {
        logger.info(logger.getResourceBundleName());
        Bank balance = new Bank(1000, 2000);
        Customer customer = new Customer();
        Bank first = new Bank("Egor", "Yahimovich",
                123, 50.0, balance.getBankBalance(), balance.getRepository());
        customer.addCustomer(first);

        Customer customer1 = new Customer();
        Bank second = new Bank("Aleksey", "Skridlevskiy",
                321, 50.0, balance.getBankBalance(), balance.getRepository());
        customer1.addCustomer(second);

        ArrayList<Customer> list = new ArrayList<>();

        list.add(customer);
        list.add(customer1);

        Cashier cashier = new Cashier(list);
        customer.start();
        customer1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cashier.start();
    }
}
