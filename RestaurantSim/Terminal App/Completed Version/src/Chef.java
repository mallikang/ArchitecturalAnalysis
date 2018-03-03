/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mallikang.2015
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
                System.out.println(ex);
            }
            System.out.println(chefName + " is now preparing " + prepare.getCourseName() + " for " + prepare.getCustomerName());
            /* 
            TO UNCOMMENT FOR REAL LIFE SIMULATION - Comment lines 42 to 48 if 
            uncommenting this segment
            //chef takes anywhere between 2 to 4.5 seconds to prepare the food
            try {
                Thread.sleep(random.nextInt(COOK_TIME));
            } catch (InterruptedException e) {
                System.out.println(e);
            }*/
            //chef takes specfic time to prepare food - extracted from COOK_TIME_COURSE
            int time = Main.COOK_TIME_COURSE.get(prepare.getCourseName());
            try {
                Thread.sleep(random.nextInt(time));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(chefName + " has finished preparing " + prepare.getCourseName() + " for " + prepare.getCustomerName());
            try {
                Restaurant.ordersReady.offer(prepare);
            } catch (NullPointerException ex) {
                System.out.println(ex);
            }
            if(Restaurant.complete){
                terminate = true;
            }
        }
    }
}