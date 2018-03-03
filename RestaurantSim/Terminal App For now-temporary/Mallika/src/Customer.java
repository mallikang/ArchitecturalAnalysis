/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adeline Chin
 */
public class Customer implements Runnable{

    //2 seconds to eat
    private final static int EAT_TIME = 2000;
    private String customerName;
    private boolean eating = false;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void run() {
        Random random = new Random();
        int i = 0;
        while(i<Main.LIST_OF_COURSES.length){
            String courseName = Main.LIST_OF_COURSES[i];
            Dish newOrder = new Dish(courseName, customerName);
            boolean placed = false;
            if (!eating && !placed) {
                try {
                    placed = Restaurant.customersOrder.offer(newOrder, 2, TimeUnit.SECONDS);
                } catch (NullPointerException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (placed) {
                    System.out.println(customerName + " is ready to order " + courseName);
                }
            }
            if (eating) {
                System.out.println(customerName + " has now started eating " + courseName);
                //Customer takes 2 seconds to eat the food
                try {
                    Thread.sleep(random.nextInt(EAT_TIME));
                } catch (InterruptedException e) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, e);
                }
                System.out.println(customerName + " has finished eating " + courseName);
                i++;
            }
        }
    }
}
