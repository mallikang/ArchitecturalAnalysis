/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gluttonsbaysim;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mallikang.2015
 */
public class Main {

    //Number of courses per person
    public static final int COURSE_PER_PERSON = 5;
    private static final int MIN_WAITERS = 1;
    private static final int MIN_CHEFS = 2;
    private static final int MIN_TABLES = 5;
    private static final int MAX_WAITERS = 4;
    private static final int MAX_CHEFS = 3;
    private static final int MAX_TABLES = 9;
    private static final ArrayList<String> CHEF_NAMES = new ArrayList<String>() {
        {
            add("Chef Cabe");
            add("Chef Caige");
            add("Chef Cade");
        }
    };
    public static final String[] LIST_OF_COURSES = {"Soup", "Salad", "Appetizer", "Main Course", "Dessert"};
    private static final ArrayList<String> WAITER_NAMES = new ArrayList<String>() {
        {
            add("Waiter Brynn");
            add("Waiter Flynn");
            add("Waiter Quinn");
            add("Waiter Lynn");
        }
    };
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
        System.out.println("Would you like to run the Subway simulation(Single-Threaded) or the Gluttons Bay Simulation(Multi-threaded)");

        //For version 1 and 2 chef and waiters is fixed
        int chefNo = 1;
        int waiter = 1;
        
        //Dummy Comment out
        int version = 2;
        //Get value from owner (maximum 9)
        int totalTables = 3;

        String[] activeCustomers = new String[totalTables];
        String[] activeChefs = new String[chefNo];
        String[] activeWaiters = new String[waiter];
        for (int customer = 0; customer < totalTables; ++customer) {
            activeCustomers[customer] = CUSTOMER_NAMES.get(customer);
        }
        for (int chef = 0; chef < chefNo; ++chef) {
            activeChefs[chef] = CHEF_NAMES.get(chef);
        }
        for (int waiterIndex = 0; waiterIndex < waiter; ++waiterIndex) {
            activeWaiters[waiterIndex] = WAITER_NAMES.get(waiterIndex);
        }
        watch.start(); // start stopwatch  
        System.out.println("Stopwatch has started");
        if (version == 2) {
            System.out.println("Welcome to Gluttons Bay!");
            Restaurant r = new Restaurant(activeWaiters, activeChefs, activeCustomers);
            r.startDay();
        } else if (version == 1) {
            System.out.println("Welcome to Subway!");
        }
        System.out.println("The day lasted " + watch.toString());
    }

}
