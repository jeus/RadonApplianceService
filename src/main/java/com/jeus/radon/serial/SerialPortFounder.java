/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeus.radon.serial;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;

/**
 * Found Serial by send parameter Hi and wait for return true values this
 * example send Hi and Return "Hello".
 */
public class SerialPortFounder {

    /**
     *
     * @param baudRate
     * @param sendCount
     * @return
     */
    public static SerialPort serialPort = null;
    private static int baudRate = 2400;
    private static int requestTry = 3;
    private static int timeOutRead = 1000;
    private List<String> ResponseRequesList;

    public SerialPortFounder(List<String> ResponseRequesList) {
        this.ResponseRequesList = ResponseRequesList;
    }

    SerialPortFounder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getRequestTry() {
        return requestTry;
    }

    public void setRequestTry(int requestTry) {
        this.requestTry = requestTry;
    }

    public int getTimeOutRead() {
        return timeOutRead;
    }

    public void setTimeOutRead(int timeOutRead) {
        this.timeOutRead = timeOutRead;
    }

    /**
     * this value have add by this forman. Reques:Response for example send "Hi"
     * and return "Hello" "Hi:Hello"
     *
     * @return
     */
    public List<String> getResponseRequesList() {
        return ResponseRequesList;
    }

    /**
     * getList Of Parameter of requests and responses
     *
     * @param ResponseRequesList
     */
    public void addHandShake(String command , String request) {
        this.ResponseRequesList.add(command+":"+request);
    }

    public boolean connect() {

        try {
            String[] portNames = SerialPortList.getPortNames();
            for (String portName : portNames) {
                System.out.println("LOGIN TO :" + portName);
                serialPort = new SerialPort(portName);

                serialPort.openPort();//Open serial port
                serialPort.setParams(baudRate, 8, 1, 0);//Set params.
                try {
                    for (String resReq : ResponseRequesList) {
                        String[] commandExpect = resReq.split(":");

                        for (int i = 0; i <= requestTry; i++) {

                            System.out.println("TRY Number :" + i);
                            if (serialPort.writeString(commandExpect[0] + "\r\n")) {
                                System.out.println(commandExpect[0] + " Sended");
                                if (reader(serialPort, commandExpect[1])) {
                                    System.out.println("Get Answer Is True");
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialPortFounder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    

    private String reader(SerialPort serialPort, long timeOut) {
        String recived = "";
        long startTime = System.currentTimeMillis();
        try {
            while (true) {
//                System.out.println("ali");
                byte[] buffer = serialPort.readBytes(1);//Read 10 bytes from serial port
                char readLast = (char) buffer[0];
                String lastChar = new String(buffer);
                recived += lastChar;
                if (readLast == 13) {
                    System.out.print(recived);
                    recived = recived.trim();
                    System.out.println("--------------------" + recived);
                    return recived;
                }
                if (System.currentTimeMillis() - startTime > timeOut && timeOut != 0) {
                    return null;
                }
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialPortFounder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param serialPort serialPort to connection and read data.
     * @param response expected string received from serial port
     * @return
     */
    private static boolean reader(SerialPort serialPort, String response) {
        String recived = "";
        long startTime = System.currentTimeMillis();
        try {
            while (true) {
                byte[] buffer;
                try {
                    buffer = serialPort.readBytes(1, timeOutRead);//Read 10 bytes from serial port
                } catch (SerialPortTimeoutException ex) {
                    break;
                }
                char readLast = (char) buffer[0];
                String lastChar = new String(buffer);
                recived += lastChar;
                if (readLast == 13) {
                    System.out.print(recived);
                    recived = recived.trim();
                    if (recived.equals(response)) {
                        return true;
                    }
                    recived = "";
                }
                if (System.currentTimeMillis() - startTime > timeOutRead * 10) {
                    return false;
                }
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialPortFounder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
