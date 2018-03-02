/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adeline Chin
 */
public class Chef extends Thread {

    private final static int COOKING_TIME = 2000;
    private Table[] tables;
    private String chefName;
    private String[] customerNames;
   
    public Chef(Table[] tables, String chefName, String[] customerNames){
        this.tables = tables;
        this.chefName = chefName;
        this.customerNames = customerNames;
    }
    
    public String startCookCourse(String chefname, String customer, String course) {
        return "Chef " + chefname + " is now preparing " + course + " for " + customer;
    }
    
    public String cookCourse(String chefname, String customer, String course) {
        try {
            Thread.sleep(COOKING_TIME);
        } catch (InterruptedException e) {
        }
        return "Chef " + chefname + " has finished preparing " + course + " for " + customer;
    }
}

/*

    private Queue<String> cook_queue;
    private Table[] tables;
    private String chefName;
    private String[] customerNames;
    private String[][] courses;
    private int waiterNum;

    public Chef(Table[] tables, String chefName, String[] customerNames, String[][] courses, int waiterNum) {
        cook_queue = new LinkedList<String>();
        for (int i = 0; i < customerNames.length; i++) {
            for (int j = 0; j < courses.length; j++) {
                String temp = "" + customerNames[i] + "_" + courses[j];
                cook_queue.add(temp);
            }
        }
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
        
        
        for (int course = 0; course < courses.length; course++) {

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
                    Thread.sleep(random.nextInt(COOKING_TIME));
                } catch (InterruptedException e) {
                }

            }
        }

        for (int no = 0; no < tables.length; no++) {
            tables[no].cook("Finish");
        }
        System.out.println("Finish");
}*/
