package myApp.com.sql;

import myApp.com.models.Client;

import java.sql.*;

public class ClientsSQL extends BaseSQL{

    public Object[][] getClients() throws SQLException {
        int rows = getNumberOfClients();
        Object[][] c = new Object[rows][4];
        Statement st = null;
        ResultSet rs = null;
        String query = "select * from clients";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                c[i][0] = rs.getString("first_name");
                c[i][1] = rs.getString("last_Name");
                c[i][2] = rs.getString("email");
                c[i][3] = rs.getString("phonenumber");
                i++;
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get client data!");
        }
        return c;
    }
    private int getNumberOfClients() throws SQLException {
        Statement st;
        ResultSet rs;
        int numRows = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) from clients");
            while(rs.next()){
                numRows = rs.getInt("count(*)");
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get number of clients!");
        }
        return numRows;
    }

    public void addClient(Client c) throws SQLException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("INSERT INTO clients"
                    +"(first_name, last_Name, email,phonenumber)" +
                    "VALUES(?,?,?,?)");
            ps.setString(1,c.getName());
            ps.setString(2,c.getLastName());
            ps.setString(3,c.getEmail());
            ps.setString(4,c.getPhoneNum());

            int a = ps.executeUpdate();
            System.out.println("Saved successfully");
            if(ps!=null) {
                ps.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot add new client!");
        }
    }

    public void deleteClient(String email) throws SQLException {
        try{
            Statement s = con.createStatement();
            s.executeUpdate("delete from clients where email = '"+email+"'");
            s.close();
        }catch(SQLException e){
            throw new SQLException("Cannot delete client!");
        }
    }

    public void updateMySQLTable(Client c) throws SQLException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("update clients set" +
                    " first_name = ?, last_name =?, email=?" +
                    "where phonenumber = ?");

            ps.setString(1,c.getName());
            ps.setString(2,c.getLastName());
            ps.setString(3,c.getEmail());
            ps.setString(4,c.getPhoneNum());
            int a = ps.executeUpdate();
            System.out.println("Saved successfully");
            if(ps!=null) {
                ps.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot update client data!");
        }
    }
}
