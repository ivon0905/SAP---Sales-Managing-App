package myApp.com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection con = null;

    private static Connection getConnection(){
        try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales_managing",
                        "root", "Iloveprogramming.666");
                System.out.println("Connection opened!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return con;
    }

    public static Connection getInstance() {
        if(con!=null)
            return con;
        return getConnection();
    }

    public static Connection closeConnection() throws SQLException{
            con.close();
            con = null;
            System.out.println("Connection closed!");
            return con;
    }
}
