package myApp.com.sql;

import java.sql.*;

public class AccountSQL extends BaseSQL{

    public String[] getAccountData(String id) throws SQLException {
        String[] o = new String[6];
        Statement st = null;
        ResultSet rs = null;
        String query = "select * from sales_managing.sales_representatives where id = '"+id+"'";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                o[0] = rs.getString("name");
                o[1] = rs.getString("lastName");
                o[2] = rs.getString("email");
                o[3] = rs.getString("phoneNum");
                o[4] = rs.getString("brand");
            }
            st.close();
            rs.close();
        return o;
    }
}
