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
        int chef_checkpoint = 0; //to alternate the chef
        int waiter_checkpoint = 0; //to alternate the waiter
        
        for (int course = 0; course < listOfCourses.length; course++) {
            //salad
            for (int customer = 0; customer < totalTables; customer++) {
                //cust 1 serve by chef 1 and waiter 1
                //cust 2 serve bt chef 2, waiter 2
                
                //assgin chef
                if (chef_checkpoint < chefNo) {
                    chef_checkpoint++;
                } else {
                    chef_checkpoint = 0;
                }
                
                //assign waiter
                if (waiter_checkpoint < waiter) {
                    waiter_checkpoint++;
                } else {
                    waiter_checkpoint = 0;
                }
                 String chefString = Integer.toString(chef_checkpoint);
                 String waiterString = Integer.toString(waiter_checkpoint);
                 String customerString = Integer.toString(customer);
                
                Chef.cook(chefString, waiterString, customerString, listOfCourses[course]);
            }

        }

        /*
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

        }
         */
    }

}
