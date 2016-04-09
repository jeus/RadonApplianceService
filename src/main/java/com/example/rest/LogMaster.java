/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
/*this class show every of log get from particle detector and every row in logs table in radon database*/
package com.example.rest;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class LogMaster {
  private final Date dateTime; //ID
  private final int radon;
  private final int hum;
  private final int temp;
  private final int press;
  private static final AtomicLong counter = new AtomicLong(100);
 
  private LogMaster(LogMasterBuilder builder){
    this.dateTime = builder.dateTime;
    this.radon = builder.radon;
    this.hum = builder.hum;
    this.temp = builder.temp;
    this.press = builder.press;

  }
  
  public LogMaster(){
    LogMaster cust = new LogMaster.LogMasterBuilder().id().build();
      this.dateTime = cust.getDateTime();
      this.radon = cust.getRadon();
      this.hum = cust.getHum();
      this.temp = cust.getTemp();
      this.press = cust.getPress();
     
  }
  
  public LogMaster(long id, int radon, int hum,
      int temp, int press){
      LogMaster cust = new LogMaster.LogMasterBuilder().id()
           .radon(radon)
           .hum(hum)
           .temp(temp)
           .press(press)
           .build();
      this.dateTime = cust.getDateTime();
      this.radon = cust.getRadon();
      this.hum = cust.getHum();
      this.temp = cust.getTemp();
      this.press = cust.getPress();
  }
  
  public Date getDateTime(){
    return this.dateTime;
  }
  
  public int getRadon() {
    return this.radon;
  }

  public int getHum() {
    return this.hum;
  }
  
  public int getTemp(){
    return this.temp;
  }

  public int getPress() {
    return this.press;
  }

  
  @Override
  public String toString(){
    return "Date Time: " + dateTime 
        + " radon: " + radon
        + " hum: " + hum 
        + " temp: " + temp 
        + " Press: " + press;
  }  
  
  public static class LogMasterBuilder{
    private Date dateTime;
    private int radon;
    private int hum ;
    private int temp ;
    private int press ;

    
    public LogMasterBuilder id(){
      this.dateTime = new Date();
      return this;
    }

    public LogMasterBuilder dateTime(Date dateTime){
      this.dateTime = dateTime;
      return this;
    }
    
    public LogMasterBuilder radon(int radon){
      this.radon = radon;
      return this;
    }

    public LogMasterBuilder hum(int hum){
      this.hum = hum;
      return this;
    }
    
    public LogMasterBuilder temp(int temp){
      this.temp = temp;
      return this;
    }
    
    public LogMasterBuilder press(int press){
      this.press = press;
      return this;
    }
    
    public LogMaster build(){
      return new LogMaster(this);
    }
    
  }    
}
