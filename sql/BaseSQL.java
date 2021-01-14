package myApp.com.sql;

import myApp.com.common.DBConnection;

import java.sql.Connection;

public class BaseSQL {
    public Connection con;

    BaseSQL(){
        con = DBConnection.getInstance();
    }
}
