/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeus.radon.serial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author jeus
 */
public class SerialReaderControler implements Runnable {

    public static SerialPort serialPort;

    @Override
    public void run() {

        SerialPortFounder founder = new SerialPortFounder();
        founder.addHandShake("Hi", "Hello");
        founder.addHandShake("uname", "123344PE");

        if (founder.connect())
        {

        }else {
                
                }

    }

}
