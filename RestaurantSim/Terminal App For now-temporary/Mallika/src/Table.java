/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author Adeline Chin
 */
public class Table extends Thread {

    private String courseName = "";
    private boolean checkIfTableIsEmpty = true;
    private final static int EAT_TIME = 6000;

    public Table() {

    }

    public synchronized void serve(String course) {
        //when there is still food on the table
        while (!checkIfTableIsEmpty) {// wait while there is still a course on the table
            try {
                wait();
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
        this.checkIfTableIsEmpty = false;
        this.courseName = course;
    }

    public boolean isTableEmpty() {
        return checkIfTableIsEmpty;
    }

    private void delay(int timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String eat() {
        Random random = new Random();

        if (checkIfTableIsEmpty) {
            //need 2 seconds to eat
            delay(random.nextInt(EAT_TIME));
        }
        this.checkIfTableIsEmpty = true;
        return courseName;
    }
}
