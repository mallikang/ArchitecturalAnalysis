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
public class StockMarketDAO {

    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    public static ArrayList<StockMarket> getAllStockMarkets() {

        ArrayList<StockMarket> smList = new ArrayList<>();
        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT * FROM stock_market;"
            );
            rs = stmt.executeQuery();
            StockMarket sm = null;
            while (rs.next()) {
                int stockId = rs.getInt(1);
                String stockName = rs.getString(2);
                String shortName = rs.getString(3);
                double buyprice = rs.getDouble(4);
                double sellprice = rs.getDouble(5);
                double totalChange = rs.getDouble(6);
                double percentChange = rs.getDouble(7);

                sm = new StockMarket(stockId, stockName, shortName, buyprice, sellprice, totalChange, percentChange);
                smList.add(sm);
            }
            return smList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return smList;
    }

    public static StockMarket getStockMarket(int stockId) {
        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT * FROM stock_market WHERE "
                    + "stockid = ?"
            );
            stmt.setInt(1, stockId);
            rs = stmt.executeQuery();

            StockMarket sm = null;
            while (rs.next()) {
                String stockName = rs.getString(2);
                String shortName = rs.getString(3);
                double buyprice = rs.getDouble(4);
                double sellprice = rs.getDouble(5);
                double totalChange = rs.getDouble(6);
                double percentChange = rs.getDouble(7);

                sm = new StockMarket(stockId, stockName, shortName, buyprice, sellprice, totalChange, percentChange);
            }
            return sm;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return null;
    }

}
