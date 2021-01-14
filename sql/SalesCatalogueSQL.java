package myApp.com.sql;

import myApp.com.models.Sale;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SalesCatalogueSQL extends BaseSQL{

    public int getNumberOfSales(String ID) throws SQLException {
        Statement st;
        ResultSet rs;
        int numRows = 0;
        String[] brand = new AccountSQL().getAccountData(ID);
            st = con.createStatement();
            rs = st.executeQuery("select count(*) from sales where brand = '"+brand[4]+"'");
            while(rs.next()){
                numRows = rs.getInt("count(*)");
            }
            st.close();
            rs.close();
        return numRows;
    }

    public Object[][] getSales(String ID) throws SQLException {
        int rows = getNumberOfSales(ID);
        Object[][] s = new Object[rows][6];
        String[] brand = new AccountSQL().getAccountData(ID);
        Statement st;
        ResultSet rs;
        String sql = "SELECT * FROM sales WHERE brand = '"+brand[4]+"'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()){
                s[i][0] = rs.getString("idOfProduct");
                s[i][1] = rs.getInt("quantity");
                s[i][2] = rs.getDouble("price");
                s[i][3] = rs.getDouble("finalPrice");
                s[i][4] = rs.getString("brand");
                s[i][5] = rs.getString("dateYMD");
                i++;
            }
            st.close();
            rs.close();
        return s;
    }

    public void addSale(Sale s) throws SQLException {
        PreparedStatement ps = null;
            ps = con.prepareStatement("INSERT INTO sales" +
                    "(idOfProduct, quantity, price, finalPrice, brand, dateYMD)" +
                    "VALUES(?,?,?,?,?,STR_TO_DATE(?,'%Y-%m-%d'))");
            ps.setString(1,s.getId());
            ps.setInt(2,s.getQuantity());
            ps.setDouble(3,s.getPrice());
            ps.setDouble(4,s.getFinalPrice());
            ps.setString(5,s.getBrand());
            ps.setString(6, getDate());
            ps.executeUpdate();
            System.out.println("Saved successfully");
            if(ps!=null) {
                ps.close();
            }
    }

    public void deleteSale(String id) throws SQLException {
            Statement s = con.createStatement();
            s.executeUpdate("delete from sales where idOfProduct = '"+id+"'");
            s.close();
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
