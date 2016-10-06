/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeus.radon.database;

import com.jeus.radon.entity.RadonLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

    public String getFileName() {
        try {
            Date start = new Date();
            Date end = new Date();
            Connection connection = new Connection();
            java.sql.Connection con = connection.getConnection();
            Statement statement = con.createStatement();

            String sqlEnd = "SELECT DateId from logs order by dateId DESC limit 1";
            String sqlStart = "SELECT DateId from logs order by dateId limit 1";

            ResultSet rs = statement.executeQuery(sqlStart);
            while (rs.next()) {
                start = rs.getTimestamp("dateId");
            }
            rs = statement.executeQuery(sqlEnd);
            while (rs.next()) {
                end = rs.getTimestamp("dateId");
            }
            con.close();

            SimpleDateFormat startDateFormat = new SimpleDateFormat("YY_mm_dd");
            String fileName = "From_" + startDateFormat.format(start);

            SimpleDateFormat endDateFormat = new SimpleDateFormat("YY_mm_dd_hh:mm:ss");
            fileName = fileName + "_To_" + endDateFormat.format(end);

            return fileName;
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
            SimpleDateFormat endDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            while (rs.next()) {
                csvStrBuilder.append(endDateFormat.format(rs.getTimestamp("dateId").getTime()));
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getInt("radon"));
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getFloat("hum"));
                csvStrBuilder.append(",");
                csvStrBuilder.append(rs.getFloat("temp"));
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

    public void DeleteAll() {
        try {
            Connection connection = new Connection();
            java.sql.Connection con = connection.getConnection();
            String sql = "DELETE FROM logs";
            Statement statement = con.createStatement();
            System.out.println("-------------------------------------->>>>");
            int count = statement.executeUpdate(sql);
            System.out.println("COUNT = " + count);
//            while (rs.next()) {
//                System.out.println("Remove ");
//            }
            con.close();
//            return csvStrBuilder.toString();
        } catch (Exception e) {
            System.out.println("EXCEPTION WHEN DELETE DATA " + e.getMessage());
//            return null;
        }
    }

    private RadonLog resultSetToObject(ResultSet rs) {
        try {
            RadonLog log = new RadonLog.LogMasterBuilder().setDateTime(rs.getTimestamp("dateId").getTime())
                    .setRadon(rs.getInt("radon"))
                    .setHum(rs.getFloat("hum"))
                    .setTemp(rs.getFloat("temp"))
                    .setPress(rs.getInt("pres"))
                    .build();
            return log;
        } catch (SQLException ex) {
            Logger.getLogger(RadonPull.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
