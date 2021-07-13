/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fu.swt.user.test;

import com.fu.swt.user.UserDAO;
import com.fu.swt.util.DBHelper;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author RiceShower
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DBHelper.class, UserDAO.class})
public class UserDAOTest {
    
    UserDAO dao;
    UserDAO daoSpy;
    
    public UserDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new UserDAO();
        PowerMockito.mockStatic(DBHelper.class);
        daoSpy = PowerMockito.spy(dao);
        
    }
    
    @After
    public void tearDown() {
    }

//CREATE

    @Test
    public void testCreate_GoodInput_ExpectedTrue() 
            throws Exception, ClassNotFoundException, NamingException, Throwable {
        String username = "ad";
        String password = "admin2";
        String fullname = "manager";
        String email = "manager@swt.com";

        dao.create(username, password, fullname, email);
        
        
    }
    
    @Test (expected = Throwable.class)
    public void testCreate_EmptyInput_ExpectedThrowable() 
            throws  Throwable{
        String username = "";
        String password = "";
        String fullname = "";
        String email = "";
 
        dao.create(username, password, fullname, email);

    }
    
    @Test (expected = Throwable.class)
    public void testCreate_LongUsername_ExpectedThrowable() 
            throws  Throwable{
        String username = "123456789010";
        String password = "admin";
        String fullname = "admin";
        String email = "admin@swt.com";
 
        dao.create(username, password, fullname, email);

    }
 
//READ
    
    @Test 
    public void testRead_GoodInput_ExpectedTrue() 
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "admin";
        String password = "admin";
       
        dao.read(username, password);
    }
    
    @Test (expected = Throwable.class)
    public void testRead_EmptyInput_ExpectedThrowable() 
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "";
        String password = "";

        dao.read(username, password);
            
    }
    
    @Test (expected = Throwable.class)
    public void testRead_LongUsername_ExpectedThrowable() 
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "1231231231";
        String password = "";

        dao.read(username, password);
            
    }
//UPDATE
    
    @Test
    public void testUpdate_GoodInput_ExpectedTrue() 
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "admin";
        String password = "admin";
        String name = "";
        String email = "";
        dao.update(username, password, name, email);
    }
    
    @Test (expected = Throwable.class)
    public void testUpdate_EmptyUsername_ExpectedThrowable() 
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "";
        String password = "admin";
        String name = "";
        String email = "";
        dao.update(username, password, name, email);
    }
    
//DELETE

    @Test
    public void testDelete_GoodUsername_ExpectedTrue()
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "admin";
        dao.delete(username);
    }
   
    @Test (expected = Throwable.class)
    public void testDelete_EmptyUsername_ExpectedThrowable()
            throws ClassNotFoundException, NamingException, Throwable {
        String username = "";
        dao.delete(username);
    }
    
}
