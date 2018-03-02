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
public class Chef extends Thread {

    final static int COURSE_PER_PERSON = 3;
    private final static int COOKING_SERVING_TIME = 2000;

    private Table[] tables;
    private String chefName;
    private String[] customerNames;
    private String[][] courses;
    private int waiterNum;

    public Chef(Table[] tables, String chefName, String[] customerNames, String[][] courses, int waiterNum) {
        this.tables = tables;
        this.chefName = chefName;
        this.customerNames = customerNames;
        this.courses = courses;
        this.waiterNum = waiterNum;
    }

    @Override
    public void run() {
        Random random = new Random();
        int checkpoint = 0;

        for (int course = 0; course < COURSE_PER_PERSON; course++) {

            for (int customer = 0; customer < customerNames.length; customer++) {
                //assign waiter
                if (checkpoint < waiterNum) {
                    checkpoint++;
                } else {
                    checkpoint = 0;
                }

                System.out.println("Chef " + this.chefName + " is cooking "
                        + courses[course][customer]);
                //tables[customer].cook(courses[course][customer]);

                //alternate the waiter
                System.out.println("Waiter " + checkpoint + " is serving Customer " + customerNames[customer] + ": "
                        + courses[course][customer]);
                tables[customer].serve(courses[course][customer]);

                try {
                    Thread.sleep(random.nextInt(COOKING_SERVING_TIME));
                } catch (InterruptedException e) {
                }

            }
        }

        for (int no = 0; no < tables.length; no++) {
            tables[no].cook("Finish");
        }
        System.out.println("Finish");
    }

}
