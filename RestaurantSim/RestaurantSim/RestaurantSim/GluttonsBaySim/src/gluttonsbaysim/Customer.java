/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gluttonsbaysim;

import java.util.Random;

/**
 *
 * @author Adeline Chin
 */
public class Customer{

    //2 seconds to eat
    private final static int EATING_TIME = 2000;

    private Table table;
    private String customerName;

    public Customer(Table table, String customerName) {
        this.table = table;
        this.customerName = customerName;
    }

    public static void eating(String customer, String courseName) {

        System.out.println("Customer " + customer + " is eating: " + courseName);
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(EATING_TIME));
        } catch (InterruptedException e) {
        }
    }
/*
    @Override
    public void run() {
        String courseName = table.eat();

        while (!courseName.equals("Finish")) {

            if (!courseName.equals("")) {
                System.out.println("Customer " + customerName + " is eating: " + courseName);
            }

            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(EATING_TIME));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            courseName = table.eat();
        }
    }
*/
}