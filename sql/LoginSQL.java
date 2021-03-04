package myApp.com.sql;

import java.sql.*;

public class LoginSQL extends BaseSQL{

    public String[] checkData(String u, String p) throws SQLException {
        Statement stat = null;
        ResultSet rs = null;
        String[] idAndAppRole = {"a","i"};
        try {
            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM users_of_app");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String app_role = rs.getString("app_role");
                String id = rs.getString("id");
                if (username.equals(u) && password.equals(p)) {
                    idAndAppRole[0] = app_role;
                    idAndAppRole[1] = id;
                    break;
                }
            }
            if (stat != null) {
                stat.close();
            }
            if (rs != null) {
                rs.close();
            }
        }catch(SQLException e){
            throw new SQLException("Cannot get user data!");
        }
        return idAndAppRole;
    }

}
