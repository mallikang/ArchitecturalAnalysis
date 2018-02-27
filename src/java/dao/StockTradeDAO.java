/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connectionDB.ConnectionManagerDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import model.StockMarket;
import model.StockTrade;

/**
 *
 * @author Adeline Chin
 */
public class StockTradeDAO {

    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    public static ArrayList<StockTrade> getAllStockTrades() {

        ArrayList<StockTrade> stList = new ArrayList<>();
        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT * FROM stock_trade;"
            );
            rs = stmt.executeQuery();
            StockTrade st = null;
            while (rs.next()) {
                String trader = rs.getString(1);
                int stockId = rs.getInt(2);
                Date tradeTime = null;
                Timestamp timestamp = rs.getTimestamp(3);
                if (timestamp != null) {
                    tradeTime = new java.util.Date(timestamp.getTime());
                }
                String tradeType = rs.getString(4);
                double price = rs.getDouble(5);
                int quantity = rs.getInt(6);

                st = new StockTrade(trader, stockId, tradeTime, tradeType, price, quantity);
                stList.add(st);
            }
            return stList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return stList;
    }

    public static ArrayList<StockTrade> getStockTradeByTrader(String trader) {
        ArrayList<StockTrade> toReturn = new ArrayList<>();
        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT * FROM stock_trade WHERE "
                    + "trader = ? "
            );
            stmt.setString(1, trader);
            rs = stmt.executeQuery();

            StockTrade st = null;
            while (rs.next()) {
                int stockId = rs.getInt(2);
                Date tradeTime = null;
                Timestamp timestamp = rs.getTimestamp(3);
                if (timestamp != null) {
                    tradeTime = new java.util.Date(timestamp.getTime());
                }
                String tradeType = rs.getString(4);
                double price = rs.getDouble(5);
                int quantity = rs.getInt(6);

                st = new StockTrade(trader, stockId, tradeTime, tradeType, price, quantity);
                toReturn.add(st);
            }
            return toReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return null;
    }

}
