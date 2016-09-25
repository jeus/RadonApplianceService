/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeus.radon.database;

import java.sql.DriverManager;

/**
 *
 * @author jeus
 */
public class Connection {

    public static String username;
    public static String password;
    public static String db_url;
    public static String jdbc_driver;

    public java.sql.Connection getConnection() {
        try {
            java.sql.Connection conn = DriverManager.getConnection(db_url, username, password);
            return conn;
        } catch (Exception ex) {
            System.out.println("CONECTION NOT CREATE  "+ex.getMessage());
            
            return null;
        }
    }

}
