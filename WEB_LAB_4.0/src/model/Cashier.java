package model;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cashier extends Thread implements Runnable {
    public static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    private int ID;
    private List<Customer> list;

    public Cashier() {
        this.list = new ArrayList<>();
    }

    public Cashier(List<Customer> customer) {
        this.list = customer;
    }

    public int getID() {
        return ID;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    public void run() {
        logger.info(currentThread().getName());
        for (Customer customer : list) {
            List<Bank> customers = customer.getCustomers();
            for (Bank bank : customers) {

                logger.info((String) bank.bankRepository(bank));
                System.out.println("Add money for customer");
                bank.addMoney(bank.getAmount());
                System.out.println(bank);

                System.out.println("Withdraw money for customer");
                bank.withdrawMoney(bank.getAmount());
                System.out.println(bank);
                logger.info((String) bank.bankRepository(bank));

                System.out.println("Money transaction");
                bank.moneyTransaction(bank.getAmount(), customer);
                System.out.println(bank);
                logger.info((String) bank.bankRepository(bank));

                System.out.println("Payment: ");
                bank.payment(bank.getAmount());
                System.out.println(bank);
                logger.info((String) bank.bankRepository(bank));

                System.out.println("Convert: ");
                System.out.println(bank.getAmount() + " USD" + " convert to " +
                        bank.convert(bank.getAmount(), "USD") + " BYN");
                System.out.println(bank.getAmount() + " EUR" + " convert to " +
                        bank.convert(bank.getAmount(), "EUR") + " BYN\n");
                logger.info((String) bank.bankRepository(bank));

                customer.getCountDownLatch().countDown();
            }
        }
    }
}
