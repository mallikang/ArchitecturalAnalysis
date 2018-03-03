

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
    private String courseName;
    private String customerName;
    
    public Dish(String courseName, String customerName){
        this.courseName = courseName;
        this.customerName = customerName;
    }
    
    public String getCourseName(){
        return courseName;
    }
    
    public String getCustomerName(){
        return customerName;
    }
}
