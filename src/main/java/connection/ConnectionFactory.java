package main.java.connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/arduinojava?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }

    }
    public static void closeConnection (Connection con){
        if (con!=null){
            try {
                con.close();
            }catch (SQLException ex){
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void closeConnection (Connection con, PreparedStatement stmt){
        if (stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        closeConnection(con);
    }
    public static void closeConnection (Connection con, PreparedStatement stmt, ResultSet rs){
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        closeConnection(con, stmt);
    }
}
