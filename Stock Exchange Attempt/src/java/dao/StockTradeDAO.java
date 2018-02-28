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
import java.text.SimpleDateFormat;
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
    
        public static ArrayList<StockTrade> getSpecificStockTradeByTrader(String trader, int stockId) {
            ArrayList<StockTrade> toReturn = new ArrayList<>();
        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT * FROM stock_trade WHERE "
                    + "trader = ? AND" + "stockid = ?"
            );
            stmt.setString(1, trader);
            stmt.setInt(2, stockId);
            rs = stmt.executeQuery();

            StockTrade st = null;
            while (rs.next()) {
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

    public static boolean addNewBuyStock(String trader, int stockId, double price, int quantity) {

        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement("INSERT INTO stock_trade VALUES (?,?,?,?,?,?)");
            stmt.setString(1, trader);
            stmt.setInt(2, stockId);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stmt.setString(3, df.format(new Date()));
            stmt.setString(4, "Buy");
            stmt.setDouble(5, price);
            stmt.setInt(6, quantity);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return false;
    }
    
        public static boolean addNewSellStock(String trader, int stockId, double price, int quantity) {

        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement("INSERT INTO stock_trade VALUES (?,?,?,?,?,?)");
            stmt.setDouble(1, price);
            stmt.setInt(2, quantity);
            stmt.setString(3, trader);
            stmt.setInt(4, stockId);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return false;
    }
}
