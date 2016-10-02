
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
  private final float hum;
  private final float temp;
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
  
  public RadonLog(long id, int radon, float hum,
      float temp, int press){
      RadonLog cust = new RadonLog.LogMasterBuilder().id()
           .setRadon(radon)
           .setHum(hum)
           .setTemp(temp)
           .setPress(press)
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

  public float getHum() {
    return this.hum;
  }
  
  public float getTemp(){
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
    private float hum ;
    private float temp ;
    private int press ;

    
    public LogMasterBuilder id(){
      this.dateTime = new Date();
      return this;
    }

    public LogMasterBuilder setDateTime(Date dateTime){
      this.dateTime = dateTime;
      return this;
    }
    public LogMasterBuilder setDateTime(long dateTime){
        this.dateTime = new Date(dateTime);
      return this;
    }
    public LogMasterBuilder setRadon(int radon){
      this.radon = radon;
      return this;
    }

    public LogMasterBuilder setHum(float hum){
      this.hum = hum;
      return this;
    }
    
    public LogMasterBuilder setTemp(float temp){
      this.temp = temp;
      return this;
    }
    
    public LogMasterBuilder setPress(int press){
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
