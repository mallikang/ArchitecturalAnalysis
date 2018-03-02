
import java.util.Random;

/**
 *
 * @author Jared Heidt
 */
public class Waiter implements Runnable {

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
    public Waiter(Table[] tables, String waiterName, String[] customerNames, String[][] courses) {
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
    public void run() {
        Random random = new Random();

        for (int course = 0; course < GluttonsBaySim.COURSE_PER_PERSON; ++course) {

            for (int customer = 0; customer < customerNames.length; ++customer) {
                try {
                    System.out.println(this.waiterName + " is serving " + customerNames[customer] + ": "
                            + courses[course][customer]);

                    tables[customer].serve(courses[course][customer]);

                    try {
                        Thread.sleep(random.nextInt(MAX_CUSTOMER_MILLIS));
                    } catch (InterruptedException e) {
                    }
                } catch (NullPointerException npe) {
                    System.err.println("Null message: " + npe.getMessage());
                }
            }
        }

        for (int index = 0; index < tables.length; ++index) {
            tables[index].serve("DONE");
        }
        System.out.println("DONE");
    }

}
