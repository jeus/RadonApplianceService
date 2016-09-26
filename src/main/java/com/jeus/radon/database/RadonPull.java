/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeus.radon.database;

import com.jeus.radon.entity.RadonLog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.compiler.ast.Stmnt;

/**
 *
 * @author jeus
 */
public class RadonPull {

    public RadonLog getLastLog() {
        try {
            Connection connection = new Connection();
            java.sql.Connection con = connection.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * from logs order by dateId DESC limit 1";

            ResultSet rs = statement.executeQuery(sql);
            RadonLog radonLog = null;
            while (rs.next()) {
                radonLog = resultSetToObject(rs);
            }
            con.close();
            return radonLog;
        } catch (Exception e) {
            System.out.println("EXCEPTION WHEN LOAD DATA " + e.getMessage());
            return null;
        }
    }

    public String getCsvLog() {
        try {
            Connection connection = new Connection();
            java.sql.Connection con = connection.getConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM logs order by dateId";

            ResultSet rs = statement.executeQuery(sql);
            RadonLog radonLog = null;
            StringBuilder csvStrBuilder = new StringBuilder();
            csvStrBuilder.append("Date,Radon,Humidity,Temperature,pressure\n");
            while (rs.next()) {
                csvStrBuilder.append(rs.getTimestamp("dateId").getTime());
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getInt("radon"));
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getInt("hum"));
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getInt("temp"));
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getInt("pres"));
                csvStrBuilder.append("\n");

            }
            con.close();
            return csvStrBuilder.toString();
        } catch (Exception e) {
            System.out.println("EXCEPTION WHEN LOAD DATA " + e.getMessage());
            return null;
        }
    }

    private RadonLog resultSetToObject(ResultSet rs) {
        try {
            RadonLog log = new RadonLog.LogMasterBuilder().setDateTime(rs.getTimestamp("dateId").getTime())
                    .setRadon(rs.getInt("radon"))
                    .setHum(rs.getInt("hum"))
                    .setTemp(rs.getInt("temp"))
                    .setPress(rs.getInt("pres"))
                    .build();
            return log;
        } catch (SQLException ex) {
            Logger.getLogger(RadonPull.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}