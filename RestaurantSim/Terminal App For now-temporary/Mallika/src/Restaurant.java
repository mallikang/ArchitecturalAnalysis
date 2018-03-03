
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
    }
    
    
}
