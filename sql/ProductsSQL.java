package myApp.com.sql;

import myApp.com.models.Product;

import java.sql.*;

public class ProductsSQL extends BaseSQL{

    public int getNumberOfProducts() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        int numRows = 0;
            st = con.createStatement();
            rs = st.executeQuery("select count(*) from products_catalogue");
            while(rs.next()){
                numRows = rs.getInt("count(*)");
            }
            st.close();
            rs.close();
        return numRows;
    }

    public Object[][] getProducts() throws SQLException {
        int n = getNumberOfProducts();
        Object[][] products = new Object[n][8];
        Statement st = null;
        ResultSet rs = null;
        String query = "select * from products_catalogue";
            st = con.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()){
                products[i][0] = rs.getString("product_ID");
                products[i][1] = rs.getString("brand");
                products[i][2] = rs.getString("color");
                products[i][3] = rs.getString("description");
                products[i][4] = rs.getDouble("price");
                products[i][5] = rs.getString("productType");
                products[i][6] = rs.getInt("quantity");
                products[i][7] = rs.getString("section");
                i++;
            }
            st.close();
            rs.close();
        return products;
    }

    public void addProduct(Product p) throws SQLException {
        PreparedStatement ps = null;
            ps = con.prepareStatement("INSERT INTO products_catalogue" +
                    "(product_ID, brand, color, description,price,productType,quantity,section)" +
                    "VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1,p.getId());
            ps.setString(2,p.getBrand());
            ps.setString(3,p.getColor());
            ps.setString(4,p.getDescription());
            ps.setDouble(5,p.getPrice());
            ps.setString(6,p.getType());
            ps.setInt(7,p.getQuantity());
            ps.setString(8,p.getSection());
            int a = ps.executeUpdate();
            System.out.println("Saved successfully");
            if(ps!=null) {
                ps.close();
            }
    }

    public void deleteProduct(String id) throws SQLException {
            Statement s = con.createStatement();
            s.executeUpdate("delete from products_catalogue where product_ID = '"+id+"'");
            s.close();
    }

    public void updateMySQLTable(Product p) throws SQLException {
        PreparedStatement ps = null;
            ps = con.prepareStatement("update products_catalogue set" +
                    " brand = ?, color =?, description=?,price=?,productType=?,quantity=?,section=?" +
                    "where product_ID = ?");

            ps.setString(1, p.getBrand());
            ps.setString(2, p.getColor());
            ps.setString(3, p.getDescription());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getType());
            ps.setInt(6, p.getQuantity());
            ps.setString(7, p.getSection());
            ps.setString(8, p.getId());
            int a = ps.executeUpdate();
            System.out.println("Saved successfully");
            if (ps != null) {
                ps.close();
            }
    }
}
