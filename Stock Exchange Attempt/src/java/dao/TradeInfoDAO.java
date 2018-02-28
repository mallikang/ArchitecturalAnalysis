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

/**
 *
 * @author Adeline Chin
 */
public class TradeInfoDAO {

    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    public static boolean authenticate(String username, String password) {
        try {
            conn = ConnectionManagerDatabase.getConnection();
            stmt = conn.prepareStatement("SELECT password FROM trade_info WHERE username = ? ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            String check = "";
            while (rs.next()) {
                check = rs.getString(1);
            }
            if (!check.equals(password)) {
                try {
                    return false;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManagerDatabase.close(conn, stmt, rs);
        }
        return false;
    }
}
