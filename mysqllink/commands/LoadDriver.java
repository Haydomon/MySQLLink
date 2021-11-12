package com.minesentinel.mysqllink.commands;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadDriver {
    public LoadDriver(String requestType, String requestValue, String requestCheck, String requestCheckVal) {
        System.out.println(requestValue);
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception exception) {}
        try {
            conn = DriverManager.getConnection("jdbc:mysql:minesentinel.com:2083", "minesent", "OX(rS87l6;y0aN");
            Statement st = conn.createStatement();
            System.out.println(String.format("Update xf_user SET %s=%s WHERE %s = %s", new Object[] { requestType, requestValue, requestCheck, requestCheckVal }));
            String query = String.format("Update xf_user SET %s=%s WHERE %s = %s", new Object[] { requestType, requestValue, requestCheck, requestCheckVal });
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
