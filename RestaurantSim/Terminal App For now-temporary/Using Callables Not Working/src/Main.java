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
    private static final int MIN_TABLES = 1;
    private static final int MAX_TABLES = 9;
    public static final String CHEF_NAME = "Chef Cabe";
    public static final String[] LIST_OF_COURSES = {"Soup", "Salad", "Appetizer", "Main Course", "Dessert"};
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
            totalTables = 9;
        } else if (totalTables < MIN_TABLES) {
            totalTables = 1;
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
            watch.start(); // start stopwatch  
            System.out.println("Stopwatch has started");
            r.startDay();
            company = "Gluttons Bay";
        } else if (version == 1) {
            System.out.println("Welcome to Subway!");
            company = "Subway";
            watch.start(); // start stopwatch  
            System.out.println("Stopwatch has started");

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
