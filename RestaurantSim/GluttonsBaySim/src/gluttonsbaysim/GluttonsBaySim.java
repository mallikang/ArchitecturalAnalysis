/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gluttonsbaysim;

import Restaurant.Restaurant;
import java.util.ArrayList;

/**
 *
 * @author mallikang.2015
 */
public class GluttonsBaySim {

    /**
     * @param args the command line arguments
     */
    public static final int COURSE_PER_PERSON = 3;

    public static void main(String[] args) {
        // TODO code application logic here

        //get all the numbers
        int waiter = 2;
        int chefNo = 2;
        int totalTables = 3;
        
        //creating list of menu
        String[] listOfCourses = new String[3];
        listOfCourses[0] = "Salad";
        listOfCourses[1] = "Appetizer";
        listOfCourses[2] = "Main Dish";
        
        for (int chefNum = 0; chefNum < chefNo; chefNum++) {
            String chefName = Integer.toString(chefNum);

            // create an array of tables
            Table[] tables = new Table[totalTables];

            Customer[] customers = new Customer[totalTables];
            String[] customerNames = new String[totalTables];
            String[][] customersAndCourses = new String[COURSE_PER_PERSON][totalTables];

            for (int customerNo = 0; customerNo < totalTables; customerNo++) {
                tables[customerNo] = new Table();
                String customerName = Integer.toString(customerNo);
                customerNames[customerNo] = customerName;
                customers[customerNo] = new Customer(tables[customerNo], customerName);
                for (int course = 0; course < COURSE_PER_PERSON; course++) {
                    customersAndCourses[course][customerNo] = listOfCourses[course];
                }
            }
            
            Chef chefNew = new Chef(tables, chefName, customerNames, customersAndCourses, waiter);
            Thread waiterJob = new Thread(chefNew);
            waiterJob.start();

            for (int x = 0; x < totalTables; x++) {
                Thread cThread = new Thread(customers[x]);
                cThread.start();
            }

        }

    }

}
