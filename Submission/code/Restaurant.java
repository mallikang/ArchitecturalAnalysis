/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
    private ArrayList<String> customers;
    public static boolean complete = false;

    public Restaurant(String[] customers) {
        this.customers = new ArrayList<>(Arrays.asList(customers));
        //These numbers are to be increased upon expansion to more chefs and waiters
        this.ordersReady = new ArrayBlockingQueue(2); //Can only hold up to twice the number of waiters on the job
        this.ordersSubmitted = new ArrayBlockingQueue(2); //Can only hold up to twice the number of chefs on the job
        this.customersOrder = new ArrayBlockingQueue(customers.length); //Can only hold up to as many customers as are present in the restaurant
        this.customersServed = new LinkedBlockingDeque(2); //Can only hold up to twice the number of chefs who are serving
    }

    public void startDay() {
        Thread waiter = new Thread(new Waiter(Main.WAITER_NAME));
        Thread chef = new Thread(new Chef(Main.CHEF_NAME));
        
        ExecutorService customerService = Executors.newCachedThreadPool();
        List<Customer> execList = new ArrayList<>();
        //creating and initialising Customer objetcs and thereafter submitting to executor pool
        for (String customer : customers) {
            execList.add(new Customer(customer));
        }


        List<Future<String>> cFutures = null;
        
        try {
            waiter.start();
            chef.start();
            cFutures = customerService.invokeAll(execList);
            customerService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!customerService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        for(Future<String> customer:cFutures){
            String result ="";
            try {
                result = customer.get();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            } catch (ExecutionException ex) {
                System.out.println(ex);
            }
            System.out.println(result);
        }
        complete = true;
    }
}
