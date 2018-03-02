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

    public Table() {

    }

    public void serve(String course) {
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

    public String eat() {
        if (checkIfTableIsEmpty) {
            //need 2 seconds to eat
            delay(2000); //1000 = 1 second
        }
        this.checkIfTableIsEmpty = true;
        return courseName;
    }
}
