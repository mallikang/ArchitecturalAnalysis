
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mallikang.2015
 */
public class Waiter implements Runnable {

    private String waiterName;
    private final boolean terminate = false;
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
                System.out.println(ex);
            }
            if (serve != null) {
                System.out.println(waiterName + " is now serving" + serve.getCourseName() + " to " + serve.getCustomerName());

                //waiter takes upto 3 seconds to serve the food
                try {
                    Thread.sleep(random.nextInt(SERVE_TIME));
                    Restaurant.customersServed.offer(serve);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(waiterName + " has finished serving" + serve.getCourseName() + " to " + serve.getCustomerName());
            }
            try {
                order = Restaurant.ordersReady.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            if (order != null) {
                System.out.println(waiterName + " is now taking the order for " + order.getCourseName() + " from " + order.getCustomerName());
                //waiter takes upto 2 seconds to take the order for the food
                try {
                    Thread.sleep(random.nextInt(SERVE_TIME));
                } catch (InterruptedException e) {
                   System.out.println(e);
                }
                try {
                    Restaurant.ordersSubmitted.offer(order);
                } catch (NullPointerException ex) {
                    System.out.println(ex);
                }
                System.out.println(waiterName + " has placed the order for " + order.getCourseName() + " from " + order.getCustomerName());
            }
        }
    }
}
