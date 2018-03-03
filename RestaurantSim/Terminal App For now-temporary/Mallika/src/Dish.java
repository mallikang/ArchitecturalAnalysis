/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mallikang.2015
 */
public class Dish {
    private static String courseName;
    private static String customerName;
    
    public Dish(String courseName, String customerName){
        this.courseName = courseName;
        this.customerName = customerName;
    }
    
    public static String getCourseName(){
        return courseName;
    }
    
    public static String getCustomerName(){
        return customerName;
    }
}
