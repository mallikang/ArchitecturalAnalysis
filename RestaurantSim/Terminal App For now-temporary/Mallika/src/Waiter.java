
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jared Heidt
 */
public class Waiter implements Runnable {

    private String waiterName;
    private boolean terminate = false;
    private final Integer SERVE_TIME = 3000;

    public Waiter(String name) {
        this.waiterName = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        Dish serve = null;
        Dish order = null;
        while (!terminate) {
            try {
                serve = Restaurant.ordersReady.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Waiter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (serve != null) {
                System.out.println(waiterName + " is now serving" + serve.getCourseName() + " to " + serve.getCustomerName());

                //waiter takes upto 3 seconds to serve the food
                try {
                    Thread.sleep(random.nextInt(SERVE_TIME));
                } catch (InterruptedException e) {
                    Logger.getLogger(Waiter.class.getName()).log(Level.SEVERE, null, e);
                }
                System.out.println(waiterName + " has finished serving" + serve.getCourseName() + " to " + serve.getCustomerName());
            }
            try {
                order = Restaurant.ordersReady.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Waiter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (order != null) {
                System.out.println(waiterName + " is now taking the order for " + order.getCourseName() + " from " + order.getCustomerName());
                //waiter takes upto 2 seconds to take the order for the food
                try {
                    Thread.sleep(random.nextInt(SERVE_TIME));
                } catch (InterruptedException e) {
                    Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    Restaurant.ordersSubmitted.offer(order);
                } catch (NullPointerException ex) {
                    Logger.getLogger(Waiter.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(waiterName + " has placed the order for " + order.getCourseName() + " from " + order.getCustomerName());
            }
        }
    }
}
