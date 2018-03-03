/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mallikang.2015
 */
public class Waiter implements Runnable {

    private String waiterName;
    private boolean terminate = false;
    private final Integer SERVE_TIME = 3000;

    public Waiter(String name) {
        this.waiterName = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        Dish serve = null;
        Dish order = null;
        while (!terminate) {
            try {
                serve = Restaurant.ordersReady.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            if (serve != null) {
                System.out.println(waiterName + " is now serving " + serve.getCourseName() + " to " + serve.getCustomerName());
                /* //TO UNCOMMENT FOR REAL LIFE SIMULATION - Comment lines 43 to 48 if 
                //uncommenting this segment
                //waiter takes upto 3 seconds to serve the food
                try {
                    Thread.sleep(random.nextInt(SERVE_TIME));
                    Restaurant.customersServed.offer(serve);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }*/
                //waiter takes fixed time of 1 s to serve the food
                try {
                    Thread.sleep(1000);
                    Restaurant.customersServed.offer(serve);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(waiterName + " has finished serving " + serve.getCourseName() + " to " + serve.getCustomerName());
            }
            try {
                order = Restaurant.customersOrder.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            if (order != null) {
                System.out.println(waiterName + " is now taking the order for " + order.getCourseName() + " from " + order.getCustomerName());
                /* //TO UNCOMMENT FOR REAL LIFE SIMULATION - Comment lines 69 to 75 if 
                //uncommenting this segment
                //waiter takes upto 2 seconds to take and place the order
                try {
                    Thread.sleep(random.nextInt(2000));
                    Restaurant.customersServed.offer(order, 2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }*/
                //waiter takes fixed time of 1 s to serve the food
                try {
                    Thread.sleep(1000);
                    Restaurant.customersServed.offer(order, 2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                try {
                    Restaurant.ordersSubmitted.offer(order);
                } catch (NullPointerException ex) {
                    System.out.println(ex);
                }
                System.out.println(waiterName + " has placed the order for " + order.getCourseName() + " from " + order.getCustomerName());
            }
            if (Restaurant.complete) {
                terminate = true;
            }
        }
    }
}
