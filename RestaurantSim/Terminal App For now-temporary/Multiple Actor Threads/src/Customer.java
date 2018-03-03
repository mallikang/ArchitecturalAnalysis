/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Adeline Chin
 */
public class Customer implements Callable<String> {

    //2 seconds to eat
    private final static int EAT_TIME = 1000;
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String call() {
        Random random = new Random();
        int i = 0;
        while (i < Main.LIST_OF_COURSES.length) {
            String courseName = Main.LIST_OF_COURSES[i];
            Dish newOrder = new Dish(courseName, customerName);
            boolean placed = false;
            boolean served = false;
            try {
                placed = Restaurant.customersOrder.offer(newOrder, 2, TimeUnit.SECONDS);
            } catch (NullPointerException | InterruptedException ex) {
                System.out.println(ex);
            }
            if (placed) {
                System.out.println(customerName + " is ready to order " + courseName);
            }
            try {
                Dish next = Restaurant.customersServed.peek();
                if (next!=null && next.getCourseName().equals(courseName) && next.getCustomerName().equals(customerName)) {
                    System.out.println(customerName + " has now started eating " + courseName);
                    //Customer takes 2 seconds to eat the food
                    try {
                        Restaurant.customersServed.take();
                        Thread.sleep(random.nextInt(EAT_TIME));
                    } catch (InterruptedException e) {
                       System.out.println(e);
                    }
                    System.out.println(customerName + " has finished eating " + courseName);
                    i++;
                }
            } catch (NullPointerException ex) {
               System.out.println(ex);
            }
        }
        return customerName;
    }
}
