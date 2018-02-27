/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;

/**
 *
 * @author Adeline Chin
 */
public class StockTrade {
    
    private String trader;
    private int stockId;
    private DateFormat tradetime;
    private String tradeType;
    private double price;
    private int quantity;

    public StockTrade(String trader, int stockId, DateFormat tradetime, String tradeType, double price, int quantity) {
        this.trader = trader;
        this.stockId = stockId;
        this.tradetime = tradetime;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public DateFormat getTradetime() {
        return tradetime;
    }

    public void setTradetime(DateFormat tradetime) {
        this.tradetime = tradetime;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
