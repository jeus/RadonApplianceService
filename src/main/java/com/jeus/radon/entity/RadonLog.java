
package com.jeus.radon.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h1>Entity Class</h1>
 * this class entity of received record from appliance <p>
 * <b>master field has:</b><p>
 * dateTime (<i>is key</i>)<p>
 * hum   (<i> humidity </i>)<p>
 * temp  (<i> temperature </i>)<p>
 * press (<i> pressure </i>)<p>
 * radon (<i> redon </i>)<p>
 * @author jeus
 */

public class RadonLog {
  private final Date dateTime; //ID
  private final int radon;
  private final int hum;
  private final int temp;
  private final int press;
  private static final AtomicLong counter = new AtomicLong(100);
 
  private RadonLog(LogMasterBuilder builder){
    this.dateTime = builder.dateTime;
    this.radon = builder.radon;
    this.hum = builder.hum;
    this.temp = builder.temp;
    this.press = builder.press;
  }
  
  public RadonLog(){
    RadonLog cust = new RadonLog.LogMasterBuilder().id().build();
      this.dateTime = cust.getDateTime();
      this.radon = cust.getRadon();
      this.hum = cust.getHum();
      this.temp = cust.getTemp();
      this.press = cust.getPress();
     
  }
  
  public RadonLog(long id, int radon, int hum,
      int temp, int press){
      RadonLog cust = new RadonLog.LogMasterBuilder().id()
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
public String getDateTimeStr(){
    DateFormat df = new SimpleDateFormat("hh:mm:ss");
    return df.format(dateTime);
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
    /**
     * <h1>this use <b>builder<b> design patter. 
     * @return {@link RadonLog}
     */
    public RadonLog build(){
      return new RadonLog(this);
    }
    
  }    
}