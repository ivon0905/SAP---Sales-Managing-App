package myApp.com.sql;

import myApp.com.models.Manager;
import myApp.com.models.NewAccount;

import java.sql.*;

public class ManagersSQL extends BaseSQL{

    public Object[][] getManagers() throws SQLException{
        int rows = getNumberOfManagers();
        Object[][] products = new Object[rows][6];
        Statement st;
        ResultSet rs;
        String query = "select * from sales_representatives";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()){
                products[i][0] = rs.getString("id");
                products[i][1] = rs.getString("name");
                products[i][2] = rs.getString("lastName");
                products[i][3] = rs.getString("email");
                products[i][4] = rs.getString("phoneNum");
                products[i][5] = rs.getString("brand");
                i++;
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get sales representative data!");
        }
        return products;
    }

    public int getNumberOfManagers() throws SQLException{
        Statement st;
        ResultSet rs;
        int numRows = 0;
        try{
            st = con.createStatement();
            rs = st.executeQuery("select count(*) from sales_representatives");
            while(rs.next()){
                numRows = rs.getInt("count(*)");
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get number of sales representatives!");
        }
        return numRows;
    }

    public void deleteManager(String id) throws SQLException{
        try{
            Statement s = con.createStatement();
            s.executeUpdate("delete from sales_representatives where id = '"+id+"'");
            s.close();
        }catch(SQLException e){
            throw new SQLException("Cannot delete sales representative data!");
        }
    }
    public void deletePassAndUser(String id) throws SQLException{
        try{
            Statement s = con.createStatement();
            s.executeUpdate("delete from users_of_app where id = '"+id+"'");
            s.close();
        }catch(SQLException e){
            throw new SQLException("Cannot delete sales representative data!");
        }
    }

    public void updateMySQLTable(Manager m) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("update sales_representatives set" +
                    " name = ?, lastName =?, email=?,phoneNum=?,brand=?" +
                    "where id = ?");

            ps.setString(1,m.getName());
            ps.setString(2,m.getLastName());
            ps.setString(3,m.getEmail());
            ps.setString(4,m.getPhoneNum());
            ps.setString(5,m.getBrand());
            ps.setString(6,m.getId());
            ps.executeUpdate();
            System.out.println("Saved successfully");
            if(ps!=null) {
                ps.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot update sales representative data!");
        }
    }

    public Object[][] getRequests() throws SQLException{
        int rows = getNumberOfRequests();
        Object[][] r = new Object[rows][6];
        Statement st;
        ResultSet rs;
        String query = "select * from new_accounts";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()){
                r[i][0] = rs.getString("name");
                r[i][1] = rs.getString("lastName");
                r[i][2] = rs.getString("email");
                r[i][3] = rs.getString("phone");
                r[i][4] = rs.getString("brand");
                i++;
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get requests data!");
        }
        return r;
    }

    public int getNumberOfRequests() throws SQLException{
        Statement st;
        ResultSet rs;
        int numRows = 0;
        try{
            st = con.createStatement();
            rs = st.executeQuery("select count(*) from new_accounts");
            while(rs.next()){
                numRows = rs.getInt("count(*)");
            }
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get number of requests!");
        }
        return numRows;
    }

    public Manager addManagerToSQL(NewAccount n) throws SQLException {
        PreparedStatement ps = null;
        Manager m = null;
        try{
            ps = con.prepareStatement("INSERT INTO sales_representatives"
                    +"(id, name, lastName, email,phoneNum,brand)" +
                    "VALUES(?,?,?,?,?,?)");
            String id = String.valueOf(getId());
            ps.setString(1,id);
            ps.setString(2,n.getName());
            ps.setString(3,n.getLName());
            ps.setString(4,n.getEmail());
            ps.setString(5,n.getPhone());
            ps.setString(6,n.getBrand());
            ps.executeUpdate();
            getUserAndPass(id,n.getBrand());
            delete(n.getEmail());
            System.out.println("Saved successfully");
            m = new Manager(id,n.getName(),n.getLName(),n.getEmail(),n.getPhone(),n.getBrand());
            if(ps!=null) {
                ps.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot add new sales representative!");
        }
        return m;
    }

    public void getUserAndPass(String id, String brand) throws SQLException {
        Statement st;
        ResultSet rs;
        String user = "", pass="";
        String query = "select * from new_accounts where brand = '"+brand+"'";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                 user = rs.getString("username");
                 pass = rs.getString("password");
            }
            setUserAndPass(user,pass,brand,id);
            st.close();
            rs.close();
        }catch(SQLException e){
            throw new SQLException("Cannot get request data!");
        }
    }

    public void setUserAndPass(String user,String pass,String brand, String id) throws SQLException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("INSERT INTO users_of_app"
                    +"(username, password, app_role, brand, id)" +
                    "VALUES(?,?,?,?,?)");
            ps.setString(1,user);
            ps.setString(2,pass);
            ps.setString(3,"manager");
            ps.setString(4,brand);
            ps.setString(5,id);
            ps.executeUpdate();
            System.out.println("Pass and username saved successfully");
            if(ps!=null) {
                ps.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot set sales representative data!");
        }
    }

    public void delete(String email) throws SQLException {
        try{
            Statement s = con.createStatement();
            s.executeUpdate("delete from new_accounts where email = '"+email+"'");
            s.close();
        }catch(SQLException e){
            throw new SQLException("Cannot delete request data!");
        }
    }

    public void deleteRequest(NewAccount n) throws SQLException {
        try{
            Statement s = con.createStatement();
            s.executeUpdate("delete from new_accounts where email = '"+n.getEmail()+"'");
            s.close();
        }catch(SQLException e){
            throw new SQLException("Cannot delete request data!");
        }
    }

    public int getId() throws SQLException {
        Statement st;
        ResultSet rs;
        String query = "select * from sales_representatives";
        String id = "";
        int ID = 0;
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                id = rs.getString("id");
            }
            st.close();
            rs.close();
            ID = Integer.valueOf(id)+1;
        }catch(SQLException e){
            throw new SQLException("Cannot get sales representative data!");
        }
        return ID;
    }
}
