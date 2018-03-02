/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gluttonsbaysim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author mallikang.2015
 */
public class GluttonsBaySim {

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
    private static final String[] LIST_OF_COURSES = {"Soup", "Salad", "Appetizer", "Main Course", "Dessert"};
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
    
    public static Queue<String> courseCustomerQueue;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Starting as a terminal app for now - will need Scanner
        Scanner scanner = new Scanner(System.in);
        StopWatch watch = new StopWatch();

        //Prints to the terminal window
        System.out.println("Welcome owner to the Gluttons Bay Restaurant Simulator!");

        //get all the numbers
        System.out.println("Please enter the number of waiters employed (minimum 1, maximum 4): ");
        int waiter = scanner.nextInt();
        System.out.println("Please enter the number of chefs employed(minimum 2, maximum 3): ");
        int chefNo = scanner.nextInt();
        System.out.println("Please enter the number of tables that are fully occupied at a time: (minimum 5, maximum 9) ");
        int totalTables = scanner.nextInt();
        System.out.println("Note: We have some constraints, therefore values above maximum and below minimum will be converted respectively as written above!");
        /**
         * *
         * System.out.println("\nWhat version would you like to run? \n Enter 1
         * for Single Threaded \n Enter 2 for Multi-Threaded"); int version =
         * scanner.nextInt(); if(version >= 2){ version = 2; } else{ version=1;
         * } String ver = "Single-Threaded Version"; if(version==2){
         * ver="Multi-Threaded Version"; } System.out.println("You have selected
         * the " + ver + "!"); *
         */
        //ensure numbers entered are appropriate
        if (waiter < MIN_WAITERS) {
            waiter = MIN_WAITERS;
        } else if (waiter > MAX_WAITERS) {
            waiter = MAX_WAITERS;
        }
        if (chefNo < MIN_CHEFS) {
            chefNo = MIN_CHEFS;
        } else if (chefNo > MAX_CHEFS) {
            chefNo = MAX_CHEFS;
        }
        if (totalTables < MIN_TABLES) {
            totalTables = MIN_TABLES;
        } else if (totalTables > MAX_TABLES) {
            totalTables = MAX_TABLES;
        }
        watch.start(); // start stopwatch  

        Table[] activeTables = new Table[totalTables];
        Customer[] activeCustomers = new Customer[totalTables];
        String[] activeCustomersNames = new String[totalTables];
        courseCustomerQueue = new LinkedList<>();

        //creating and initialising tables, customers and Queue
        for (int customer = 0; customer < totalTables; ++customer) {
            String customerName = CUSTOMER_NAMES.get(customer);
            activeTables[customer] = new Table();
            activeCustomersNames[customer] = customerName;
            activeCustomers[customer] = new Customer(activeTables[customer], customerName);
            for (int course = 0; course<COURSE_PER_PERSON; ++course) {
                String courseName = LIST_OF_COURSES[course];
                courseCustomerQueue.add(courseName + "_" + customerName);
            }
        }
        
        //creating and initialising Chef objetcs
        for(int chefs = 0; chefs <chefNo; chefs++){
            Chef chef = new Chef(activeTables, CHEF_NAMES.get(chefs), activeCustomersNames);
        }
        

        /*if (ver.equals("Single-Threaded Version")) {
            
        } else {
            watch.start(); // start stopwatch                 
        }*/
        System.out.println("Finished simulation after " + watch.toString());
    }

}
