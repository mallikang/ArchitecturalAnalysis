
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static BlockingQueue<Dish> customersServed; //For customers waiting to place their orders
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
        this.customersServed = new ArrayBlockingQueue(waiters.length * 2); //Can only hold up to twice the number of chefs who are serving
    }

    public void startDay() {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Customer> list = new ArrayList<Customer>();
        //creating and initialising Customer objetcs and thereafter submitting to executor pool
        for (String customer : customers) {
            list.add(new Customer(customer));
        }

        Thread waiter = new Thread(new Waiter(waiters.get(0)));
        Thread chef = new Thread(new Chef(chefs.get(0)));
        
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<String>> futures = null;
        try {
            futures = pool.invokeAll(list);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        if (futures != null) {
            for (Future<String> f : futures) {
                String name = null;

                try {
                    name = f.get();
                } catch (ExecutionException ex) {
                    System.out.println(ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (name != null) {
                    System.out.println(name + " has now left the restaurant!");
                }
            }
        }
        exec.shutdownNow();

        /*
        Thread customer = new Thread(new Customer(customers.get(0)));
        
        chef.start();
        waiter.start();
        customer.start();
        try{
            customer.join();
        }catch(InterruptedException e){
            System.out.println(e);
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

         */
    }
}
