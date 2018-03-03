
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mallikang.2015
 */
public class Restaurant {

   public static BlockingQueue<Dish> ordersReady; //For Waiter to serve order that Chef has prepared
    public static BlockingQueue<Dish> ordersSubmitted; //For Chef to work on new order that is submitted by Waiter
    public static BlockingQueue<Dish> customersOrder; //For customers waiting to place their orders
    public static LinkedBlockingDeque<Dish> customersServed; //For customers waiting to place their orders
    private ArrayList<String> waiters;
    private ArrayList<String> chefs;
    private ArrayList<String> customers;

    public Restaurant(String[] waiters, String[] chefs, String[] customers) {
        this.waiters = new ArrayList<>(Arrays.asList(waiters));
        this.chefs = new ArrayList<>(Arrays.asList(chefs));
        this.customers = new ArrayList<>(Arrays.asList(customers));
        this.ordersReady = new ArrayBlockingQueue(waiters.length * 2); //Can only hold up to twice the number of waiters on the job
        this.ordersSubmitted = new ArrayBlockingQueue(chefs.length * 2); //Can only hold up to twice the number of chefs on the job
        this.customersOrder = new ArrayBlockingQueue(customers.length); //Can only hold up to as many customers as are present in the restaurant
        this.customersServed = new LinkedBlockingDeque(waiters.length*2); //Can only hold up to twice the number of chefs who are serving
    }

    public void startDay() {
        /*
        ExecutorService exec = Executors.newCachedThreadPool();
        //creating and initialising Customer objetcs and thereafter submitting to executor pool
        for (String customer : customers) {
            Customer newCustomer = new Customer(customer);
            exec.submit(newCustomer);
        }

        //creating and initialising Waiter objetcs and thereafter submitting to executor pool
        for (String waiter : waiters) {
            Waiter newWaiter = new Waiter(waiter);
            exec.submit(newWaiter);
        }

        //creating and initialising Waiter objetcs and thereafter submitting to executor pool
        for (String chef : chefs) {
            Chef newChef = new Chef(chef);
            exec.submit(newChef);
        }
        
        exec.shutdown();
        */
        
        Thread waiter = new Thread(new Waiter(waiters.get(0)));
        Thread chef = new Thread(new Chef(chefs.get(0)));
        Thread customer1 = new Thread(new Customer(customers.get(0)));
        Thread customer2 = new Thread(new Customer(customers.get(1)));
        Thread customer3 = new Thread(new Customer(customers.get(2)));
        
        chef.start();
        waiter.start();
        customer1.start();
        customer2.start();
        customer3.start();
        try{
            customer1.join();
            customer2.join();
            customer3.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}
