/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Adeline Chin
 */
public class Customer implements Runnable {

    //2 seconds to eat
    private final static int EAT_TIME = 2000;
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void run() {
        Random random = new Random();
        int i = 0;
        boolean canPlace = true;
        boolean served = false;
        boolean ordered = false;
        while (i < Main.LIST_OF_COURSES.length) {
            String courseName = Main.LIST_OF_COURSES[i];
            Dish newOrder = new Dish(courseName, customerName);
            if (!ordered) {
                try {
                    canPlace = Restaurant.customersOrder.offer(newOrder, 2, TimeUnit.SECONDS);
                } catch (NullPointerException ex) {
                    System.out.println(ex);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                if (canPlace) {
                    canPlace = false;
                    ordered = true;
                    served = false;
                    System.out.println(customerName + " is ready to order " + courseName);
                }
            }
            synchronized (Restaurant.customersServed) {
                Dish next = Restaurant.customersServed.poll();
                if (next != null) {
                    if (!next.getCourseName().equals(courseName) || !next.getCustomerName().equals(customerName)) {
                        Restaurant.customersServed.offerFirst(next);
                    } else {
                        served = true;
                    }
                }

            }
            if (served) {
                System.out.println(customerName + " has now started eating " + courseName);
                //Customer takes 2 seconds to eat the food
                try {
                    Thread.sleep(EAT_TIME);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(customerName + " has finished eating " + courseName);
                served = false;
                canPlace = true;
                ordered = false;
                i++;
                }
        }
    }
}
