package myApp.com.sql;

import java.sql.*;
import java.util.Calendar;

public class SalesAnalysisSQL extends BaseSQL{

    public Object[][] getProducts() throws SQLException {
        int rows = getNumberOfSales();
        Object[][] s = new Object[rows][6];
        Statement st;
        ResultSet rs;
        String sql = "SELECT * FROM sales";
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

    public Object[][] getSalesByBrand(String brand) throws SQLException {
        int rows = getNumberOfSales();
        Object[][] s = new Object[rows][6];
        Statement st;
        ResultSet rs;
        int i = 0;
        String query = "select * from sales where brand = '"+brand+"'";
            st = con.createStatement();
            rs = st.executeQuery(query);
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
        Object[][] sales = new Object[i][6];
        System.arraycopy(s,0,sales,0,i);
        return sales;
    }

    public Object[][] getSalesByMonth(int month) throws SQLException {
        Object[][] sales = getProducts();
        int rows = getNumberOfSales();
        Object[][] salesByMonth = new Object[rows][6];
        int h=0;
        String date;
        for(int i=0;i<rows;i++){
            date = sales[i][5].toString();
            java.sql.Date dat = java.sql.Date.valueOf(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dat);
            int m = cal.get(Calendar.MONTH);
            if(month == (m+1)){
                for(int j=0;j<6;j++){
                    salesByMonth[h][j] = sales[i][j];
                }
                h++;
            }
        }
        Object[][] s = new Object[h][6];
        System.arraycopy(salesByMonth,0,s,0,h);
        return s;
    }

    public int getNumberOfSales() throws SQLException {
        Statement st;
        ResultSet rs;
        int numRows = 0;
            st = con.createStatement();
            rs = st.executeQuery("select count(*) from sales");
            while(rs.next()){
                numRows = rs.getInt("count(*)");
            }
            st.close();
            rs.close();
        return numRows;
    }

    public String[] getBrands() throws SQLException {
        String[] brands = new String[10];
        int i = 0;
        String sql = "SELECT DISTINCT brand FROM sales";
        Statement st;
        ResultSet rs;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                brands[i] = rs.getString("brand");
                i++;
            }
            st.close();
            rs.close();
        String[] b = new String[i];
        System.arraycopy(brands,0,b,0, i);
        return b;
    }

    public Object[][] getSalesByBrandForChart(String brand) throws SQLException {
        Object[][] sales = getSalesByBrand(brand);
        Object[][] s = new Object[6][3];
        String date;
        int q7=0,q8=0,q9=0,q10=0,q11=0,q12=0;
        double p7=0,p8=0,p9=0,p10=0,p11=0,p12=0;
        for(int i=0;i<sales.length;i++){
            for(int j=0;j<6;j++){
                date = String.valueOf(sales[i][5]);
                java.sql.Date dat = java.sql.Date.valueOf(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dat);
                int m = cal.get(Calendar.MONTH);
                if(m==6){
                    q7 += (int)sales[i][1];
                    p7 += (double)sales[i][3];
                    s[0][2] = sales[i][5];
                }
                else if(m==7){
                    q8 += (int)sales[i][1];
                    p8 += (double)sales[i][3];
                    s[1][2] = sales[i][5];
                }
                else if(m==8){
                    q9 += (int)sales[i][1];
                    p9 += (double)sales[i][3];
                    s[2][2] = sales[i][5];
                }
                else if(m==9){
                    q10 += (int)sales[i][1];
                    p10 += (double)sales[i][3];
                    s[3][2] = sales[i][5];
                }
                else if(m==10){
                    q11 += (int)sales[i][1];
                    p11 += (double)sales[i][3];
                    s[4][2] = sales[i][5];
                }
                else if(m==11){
                    q12 += (int)sales[i][1];
                    p12 += (double)sales[i][3];
                    s[5][2] = sales[i][5];
                }
            }
        }
        s[0][0] = q7; s[0][1] = p7;
        s[1][0] = q8; s[1][1] = p8;
        s[2][0] = q9; s[2][1] = p9;
        s[3][0] = q10; s[3][1] = p10;
        s[4][0] = q11; s[4][1] = p11;
        s[5][0] = q12; s[5][1] = p12;

        return s;
    }

    public Object[][] getAllSalesForChart() throws SQLException {
        Object[][] sales = getProducts();
        Object[][] s = new Object[6][3];
        String date;
        int q7=0,q8=0,q9=0,q10=0,q11=0,q12=0;
        double p7=0,p8=0,p9=0,p10=0,p11=0,p12=0;
        for(int i=0;i<sales.length;i++){
            for(int j=0;j<6;j++){
                date = String.valueOf(sales[i][5]);
                java.sql.Date dat = java.sql.Date.valueOf(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dat);
                int m = cal.get(Calendar.MONTH);
                if(m==6){
                    q7 += (int)sales[i][1];
                    p7 += (double)sales[i][3];
                    s[0][2] = sales[i][5];
                }
                else if(m==7){
                    q8 += (int)sales[i][1];
                    p8 += (double)sales[i][3];
                    s[1][2] = sales[i][5];
                }
                else if(m==8){
                    q9 += (int)sales[i][1];
                    p9 += (double)sales[i][3];
                    s[2][2] = sales[i][5];
                }
                else if(m==9){
                    q10 += (int)sales[i][1];
                    p10 += (double)sales[i][3];
                    s[3][2] = sales[i][5];
                }
                else if(m==10){
                    q11 += (int)sales[i][1];
                    p11 += (double)sales[i][3];
                    s[4][2] = sales[i][5];
                }
                else if(m==11){
                    q12 += (int)sales[i][1];
                    p12 += (double)sales[i][3];
                    s[5][2] = sales[i][5];
                }
            }
        }
        s[0][0] = q7; s[0][1] = p7;
        s[1][0] = q8; s[1][1] = p8;
        s[2][0] = q9; s[2][1] = p9;
        s[3][0] = q10; s[3][1] = p10;
        s[4][0] = q11; s[4][1] = p11;
        s[5][0] = q12; s[5][1] = p12;

        return s;
    }

    public Object[][] getQuantityAndCostOfProductsByBrands() throws SQLException {
        Object[][] sales = getProducts();
        String[] brands = getBrands();
        int rows = brands.length;
        Object[][] s = new Object[rows][3];
        int[] quantity = new int[rows];
        Double[] cost = new Double[rows];
        for(int i=0;i<rows;i++){
            s[i][0]=0;
            s[i][1]=0;
            s[i][2]=brands[i];
            quantity[i]=0;
            cost[i]=0.00;
        }
        for(int i=0;i<sales.length;i++){
            for(int k=0;k<rows;k++){
                if(s[k][2].equals(sales[i][4])){
                    quantity[k] += (int)sales[i][1];
                    cost[k] += (double)sales[i][3];
                }
                else{
                    quantity[k] += 0;
                    cost[k] += 0;
                }
            }
        }
        for(int i=0;i<rows;i++){
            s[i][0] = quantity[i];
            s[i][1] = cost[i];
        }
        return s;
    }
}
