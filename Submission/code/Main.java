/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gluttonsbaysim;

import utility.StopWatch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author mallikang.2015
 */
public class Main {

    //Number of courses per person
    public static final int COURSE_PER_PERSON = 5;
    private static final int MIN_TABLES = 1;
    private static final int MAX_TABLES = 15;
    public static final String CHEF_NAME = "Chef Cabe";
    public static final String[] LIST_OF_COURSES = {"Soup", "Salad", "Appetizer", "Main Course", "Dessert"};
    public static final HashMap<String, Integer> COOK_TIME_COURSE = new HashMap<>();
    static {
        COOK_TIME_COURSE.put("Soup", 500);
        COOK_TIME_COURSE.put("Salad", 1000);
        COOK_TIME_COURSE.put("Appetizer", 1000);
        COOK_TIME_COURSE.put("Main Course", 2000);
        COOK_TIME_COURSE.put("Dessert", 1500);
    }
    public static final String WAITER_NAME = "Waiter Flynn";
    private static final ArrayList<String> CUSTOMER_NAMES = new ArrayList<String>() {
        {
            add("Customer Pauline");
            add("Customer Dean");
            add("Customer Eileen");
            add("Customer Eugene");
            add("Customer Irene");
            add("Customer Janine");
            add("Customer Kathleen");
            add("Customer Maxine");
            add("Customer Maureen");
            add("Customer Andy");
            add("Customer Sandy");
            add("Customer Mandy");
            add("Customer Brandy");
            add("Customer Candy");
            add("Customer Randy");
        }
    };
    //For future Expansion - not used yet
    private static final ArrayList<String> CHEF_NAMES = new ArrayList<String>() {
        {
            add("Chef Cabe");
            add("Chef Caige");
            add("Chef Cade");
        }
    };
    private static final ArrayList<String> WAITER_NAMES = new ArrayList<String>() {
        {
            add("Waiter Brynn");
            add("Waiter Flynn");
            add("Waiter Quinn");
            add("Waiter Lynn");
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Starting as a terminal app for now - will need Scanner
        Scanner scanner = new Scanner(System.in);
        StopWatch watch = new StopWatch();

        //Prints to the terminal window
        System.out.println("Welcome owner to the Gluttons Bay Restaurant Simulator!");
        System.out.println("Would you like to run the Subway simulation(Single-Threaded)[Enter 1] or the Gluttons Bay Simulation(Multi-threaded)[Enter 2]?");
        int version = scanner.nextInt();
        System.out.println("How many customers would you like to service today? [maximum 9]");
        int totalTables = scanner.nextInt();

        if (totalTables > MAX_TABLES) {
            totalTables = MAX_TABLES;
        } else if (totalTables < MIN_TABLES) {
            totalTables = MIN_TABLES;
        }

        //For version 1 and 2 chef and waiter is fixed
        String[] activeCustomers = new String[totalTables];

        for (int customer = 0; customer < totalTables; ++customer) {
            activeCustomers[customer] = CUSTOMER_NAMES.get(customer);
        }
        String company = "";
        if (version == 2) {
            System.out.println("Welcome to Gluttons Bay!");
            Restaurant r = new Restaurant(activeCustomers);
            company = "Gluttons Bay";
            watch.start(); // start stopwatch  
            System.out.println("Stopwatch has started");
            r.startDay();
        } else if (version == 1) {
            System.out.println("Welcome to Subway!");
            company = "Subway";
            watch.start(); // start stopwatch  
            System.out.println("Stopwatch has started");
            for(String customer : activeCustomers){
                System.out.println(customer + " is ready to order.");
                System.out.println(WAITER_NAME + " is now taking the order for Sub from " + customer);
                try {
                    Thread.currentThread().sleep(3000);
                    System.out.println(customer + " has chosen order of Sub.");
                    Thread.currentThread().sleep(2000);
                    System.out.println(customer + " has chosen order of Meal.");
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                System.out.println(WAITER_NAME + " has placed the order for Sub from " + customer);
                System.out.println(CHEF_NAME + " is now preparing the order for Sub from " + customer);
                try {
                    Thread.currentThread().sleep(1000);
                    System.out.println(CHEF_NAME + " has cut the bread for the order of Sub for " + customer);
                    Thread.currentThread().sleep(1000);
                    System.out.println(CHEF_NAME + " has added cheese for the order of Sub for " + customer);
                    Thread.currentThread().sleep(2000);
                    System.out.println(CHEF_NAME + " has toasted the bread for the order of Sub for " + customer);
                    Thread.currentThread().sleep(1000);
                    System.out.println(CHEF_NAME + " has added the veggies for the order of Sub for " + customer);
                    Thread.currentThread().sleep(1000);
                    System.out.println(CHEF_NAME + " has added the sauce for the order of Sub for " + customer);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                System.out.println(CHEF_NAME + " has finished preparing the order for Sub from " + customer);
                System.out.println(WAITER_NAME + " is now serving " + customer + " the order for Sub.");
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                System.out.println(WAITER_NAME + " has finished serving " + customer + " the order for Sub.");
                System.out.println(customer + " has now started eating the Sub.");
                try {
                    Thread.sleep(5000);
                    System.out.println(customer + " has finished half of her Sub.");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(customer + " has finished eating the Sub.");
                System.out.println(customer + " had a good time and is now leaving the restaurant!");
            }
        }
        System.out.println("The day at " + company + " lasted " + watch.toString());
        //In a multi-threaded version, the process is still running, despite
        //setting the while loops within Waiter and Chef to end. As the 
        //Thread.stop() method has deprecated, there is no way to forcefully end 
        //the process apart from the next line. I have not been able to identify
        // any other reason for this program to keep running, therefore, to 
        //overcome this I have used the following line:
        System.exit(0);
    }

}
