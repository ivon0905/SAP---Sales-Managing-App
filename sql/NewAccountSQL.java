package myApp.com.sql;

import myApp.com.models.NewAccount;

import java.sql.*;

public class NewAccountSQL extends BaseSQL{

    public void addNewAccount(NewAccount n, NewAccount n1) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO new_accounts"
                    + "(name, lastName, email,phone,brand,username,password)" +
                    "VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, n.getName());
            ps.setString(2, n.getLName());
            ps.setString(3, n.getEmail());
            ps.setString(4, n.getPhone());
            ps.setString(5, n.getBrand());
            ps.setString(6, n1.getUser());
            ps.setString(7, n1.getPass());
            ps.executeUpdate();
            System.out.println("Saved successfully");
            if (ps != null) {
                ps.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot add new manager!");
        }
    }
}
