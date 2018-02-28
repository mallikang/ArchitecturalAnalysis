package connectionDB;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that manages connections to the database. It also has a utility
 * method that close connections, statements and resultsets
 */
public class ConnectionManagerDatabase {

    private static final String PROPS_FILENAME = "connection.properties";
    private static String dbUser;
    private static String dbName;
    private static String dbPassword;
    private static String dbURL;

    static {
        readDatabaseProperties();
        initDBDriver();
    }

    private static void readDatabaseProperties() {
        InputStream is = null;
        try {
            // Retrieve properties from connection.properties via the CLASSPATH
            // WEB-INF/classes is on the CLASSPATH
            is = ConnectionManagerDatabase.class.getResourceAsStream(PROPS_FILENAME);
            Properties props = new Properties();
            props.load(is);
            is.close();

            // load database connection details
            String host = props.getProperty("db.host");
            String port = props.getProperty("db.port");
            dbName = props.getProperty("db.name");
            dbUser = props.getProperty("db.user");

            // grab environment variable to check if we are on production environment
            String hostOS = System.getProperty("os.name");
            if (hostOS.equals("Linux")) {
                InetAddress ip;
                ip = InetAddress.getLocalHost();
                if(ip.getHostName().equals("UAT-Server")){
                    dbPassword = props.getProperty("testserver.db.password");
                }else{
                    dbPassword = props.getProperty("productionserver.db.password");
                }
                
            } else {
                // in local environment, use db.password
                dbPassword = props.getProperty("db.password");
            }

            dbURL = "jdbc:mysql://" + host + port + "/" + dbName + "?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true";
        } catch (Exception ex) {
            // unable to load properties file
            String message = "Unable to load '" + PROPS_FILENAME + "'.";

            System.out.println(message);
            Logger.getLogger(ConnectionManagerDatabase.class.getName()).log(Level.SEVERE, message, ex);
            throw new RuntimeException(message, ex);
        }
    }

    private static void initDBDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            // unable to load properties file
            String message = "Unable to find JDBC driver for MySQL.";

            System.out.println(message);
            Logger.getLogger(ConnectionManagerDatabase.class.getName()).log(Level.SEVERE, message, ex);
            throw new RuntimeException(message, ex);
        }
    }

    /**
     * Gets a connection to the database
     *
     * @return the connection
     * @throws SQLException if an error occurs when connecting
     */
    public static Connection getConnection() throws SQLException {
        String message = "dbURL: " + dbURL
                + "  , dbUser: " + dbUser
                + "  , dbPassword: " + dbPassword;
        Logger.getLogger(ConnectionManagerDatabase.class.getName()).log(Level.INFO, message);

        return DriverManager.getConnection(dbURL, dbUser, dbPassword);

    }

    /**
     * close the given connection, statement and resultset
     *
     * @param conn the connection object to be closed
     * @param stmt the statement object to be closed
     * @param rs the resultset object to be closed
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManagerDatabase.class.getName()).log(Level.WARNING,
                    "Unable to close ResultSet", ex);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManagerDatabase.class.getName()).log(Level.WARNING,
                    "Unable to close Statement", ex);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManagerDatabase.class.getName()).log(Level.WARNING,
                    "Unable to close Connection", ex);
        }
    }

    /**
     * close the given connection and statement
     *
     * @param conn the connection object to be closed
     * @param stmt the statement object to be closed
     */
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    /**
     * close the given connection
     *
     * @param conn the connection object to be closed
     */
    public static void close(Connection conn) {
        close(conn, null, null);
    }

    public static String returnSystemEnvironment() {
        return dbURL;
    }

}