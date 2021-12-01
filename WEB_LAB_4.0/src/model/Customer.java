package model;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;


public class Customer extends Thread implements Runnable {
    public static final Logger logger = Logger.getLogger(Main.class.getName());
    private static int id;
    private final int ID;
    private List<Bank> customers;
    private CountDownLatch countDownLatch;
    protected double balance;

    public Customer() {
        this.ID = id++;
        this.countDownLatch = new CountDownLatch(1);
        this.customers = new ArrayList<>(2);
    }

    public int getID() {
        return ID;
    }

    public double getBalance() {
        return balance;
    }

    public List<Bank> getCustomers() {
        return customers;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCustomers(List<Bank> customers) {
        this.customers = customers;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void addCustomer(Bank customer) {
        customers.add(customer);
    }

    public void run() {
        logger.info(currentThread().getName());
        for (Bank customer : customers) {
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Change amount : " + customer.getID());
            customer.setAmount(new Random().nextInt(1000));
            System.out.println("Amount: " + customer.getAmount() + "\n");

            System.out.println("Customer balance: " + customer.getID());
            customer.setBalance(new Random().nextInt(500));
            System.out.println("Balance: " + customer.getBalance() + "\n");

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
