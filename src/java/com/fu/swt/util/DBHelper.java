/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fu.swt.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;
/**
 *
 * @author RiceShower
 */
public class DBHelper {

    public static Connection makeConnection()
            throws NamingException, SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SWT";
        Connection con = DriverManager.getConnection(url, "sa", "123456");
        return con;
    }
}
