
import java.util.Random;

/**
 *
 * @author Jared Heidt
 */
public class Waiter{
/*
    private final static int MAX_CUSTOMER_MILLIS = 6000;// must wait for between 0-4 seconds

    private Table[] tables;
    private String waiterName;
    private String[] customerNames;
    private String[][] courses;

    /**
     * initializes the data members of the class
     *
     * @param tables array of Table objects this Waiter waits on
     * @param waiterName name of the waiter
     * @param customerNames names of Customers served by the Waiter
     */
/*    public Waiter(Table[] tables, String waiterName, String[] customerNames, String[][] courses) {
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    /**
     * For each customer, a thread on this Waiter object serves the three
     * courses in the correct order by calling the serve() method in the
     * corresponding Table, prints out what course is served to which Customer,
     * and sleeps for a random time between 0 & 4 seconds to mimic time taken in
     * serving.
     */
/*    public void makeServe() throws InterruptedException {
        synchronized (r) {
            r.notifyAll();
            while(r.orderMadeByClient==false)
                r.wait();
            System.out.println("Start serving order ");
            Thread.sleep(1000);
            r.putOrder(r.getOrderMadeByClient());
            while(r.orderReady==false)
                r.wait();
            r.putOrderReceived(r.getFood());   
            System.out.println("End serving order ");
        }
    }*/

}
