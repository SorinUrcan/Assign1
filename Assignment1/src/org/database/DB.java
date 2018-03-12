package org.database;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sorin
 */
public class DB {
    private static DB database = null;
    private static Connection conn = null;
    private static Statement stat = null;

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String JDBC_URL = "jdbc:mysql://localhost:3306/Bank";

    final static String userName = "root";
    final static String userPassword = "root";
    
    private DB(){
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = (Connection) DriverManager.getConnection(JDBC_URL, userName, userPassword);
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stat = (Statement) conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DB getDBInstance(){
        if(database == null)
                database = new DB();

        return database;
    }
    
    // update information in the database
    public void executeUpdate(String query){
        try {
            stat.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // read information from database
    public ResultSet executeQuery(String query){
        ResultSet res = null;
        try {
            res = stat.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public String login(String username, String password){
        try {
            ResultSet res;
            StringBuilder query = new StringBuilder();
            query.append("select * from UserAccounts where userName='");
            query.append(username);
            query.append("';");
            res = DB.getDBInstance().executeQuery(query.toString());
            
            if (!res.first()) 
                return "wrong";
            
            res.first();
            if(res.getString("password").equals(password)){
                return res.getString("userType");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "wrong";
    }
    
    public int userId(String username){
        try {
            ResultSet res;
            StringBuilder query = new StringBuilder();
            query.append("select * from UserAccounts where userName='");
            query.append(username);
            query.append("';");
            res = DB.getDBInstance().executeQuery(query.toString());
            res.first();
            return res.getInt("ID");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
