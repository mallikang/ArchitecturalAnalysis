
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adeline Chin
 */
public class Chef implements Runnable {

    private String chefName;
    private boolean terminate = false;
    private final Integer COOK_TIME = 4500;

    public Chef(String name) {
        this.chefName = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        Dish prepare = null;
        while (!terminate) {
            try {
                prepare = Restaurant.ordersSubmitted.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(chefName + " is now preparing " + prepare.getCourseName() + " for " + prepare.getCustomerName());
            //chef takes anywhere between 2 to 4.5 seconds to prepare the food
            try {
                Thread.sleep(random.nextInt(COOK_TIME));
            } catch (InterruptedException e) {
                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, e);
            }
            System.out.println(chefName + " has finished preparing " + prepare.getCourseName() + " for " + prepare.getCustomerName());
            try {
                Restaurant.ordersReady.offer(prepare);
            } catch (NullPointerException ex) {
                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setTerminate(boolean terminate){
        this.terminate = terminate;
    }
}