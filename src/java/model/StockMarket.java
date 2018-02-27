/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adeline Chin
 */
public class StockMarket {
    
    private int stockId;
    private String stockName;
    private String shortName;
    private double price;
    private double change;
    private double percent_change;

    public StockMarket(int stockId, String stockName, String shortName, double price, double change, double percent_change) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.shortName = shortName;
        this.price = price;
        this.change = change;
        this.percent_change = percent_change;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getPercent_change() {
        return percent_change;
    }

    public void setPercent_change(double percent_change) {
        this.percent_change = percent_change;
    }
    
    
    
}
