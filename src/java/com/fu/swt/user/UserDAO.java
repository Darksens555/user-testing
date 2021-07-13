/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fu.swt.user;

/**
 *
 * @author RiceShower
 */
import com.fu.swt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author nguye
 */
public class UserDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void CloseAll() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public boolean create(String username, String password, String fullname, String email)
            throws SQLException, ClassNotFoundException, NamingException, Throwable {

        if (username.isEmpty()) {
            throw new Throwable("Username cannot be empty.");
        }
        if(username.length() > 10 ){
            throw new Throwable ("Invalid username length.");
        }

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into User(usrname, password, fullname, email) "
                        + "VALUES(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setString(4, email);

                int rowsInserted = stm.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("New user created.");
                }
            }
        } finally {
            CloseAll();
        }

        return false;

    }

    public boolean read(String username, String password)
            throws SQLException, ClassNotFoundException, NamingException, Throwable {

        if (username.isEmpty() || password.isEmpty()) {
            throw new Throwable ("Username or password empty");
        }
        
        if(username.length() > 10 ){
            throw new Throwable ("Invalid username length.");
        }

        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select username "
                        + "From Users "
                        + "Where username = ? and password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();

                if (rs.next()) {
                    return true;
                }

            }
        } finally {
            CloseAll();
        }

        return false;
    }

    public boolean update(String username, String password, String name, String email)
            throws SQLException, ClassNotFoundException, NamingException, Throwable {

        if (username.isEmpty()) {
            throw new Throwable ("Username empty");
        }
        
        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Users "
                        + "Where username = ? "
                        + "Set Password = ?, Name = ?, E,ail = ?  ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, name);
                stm.setString(4, email);

                int rowsUpdated = stm.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An existing user was updated successfully!");
                }

            }
        } finally {
            CloseAll();
        }

        return false;
    }

    

    public boolean delete(String username)
            throws SQLException, ClassNotFoundException, NamingException, Throwable {

        if (username.isEmpty()) {
            throw new Throwable ("Username empty");
        }
        
        try {

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Delete username "
                        + "From Users "
                        + "Where username = ? ";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                int rowsDeleted = stm.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("User deleted.");
                }

            }
        } finally {
            CloseAll();
        }

        return false;
    }
    
    
}
