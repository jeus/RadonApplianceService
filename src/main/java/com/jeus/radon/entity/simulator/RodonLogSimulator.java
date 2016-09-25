/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
package com.jeus.radon.entity.simulator;

import com.jeus.radon.entity.RadonLog;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
/**
 * <h1>Simulate log received from appliance<h1><p>
 * this class implement from <b>Runnable</b> and can run multi instance and run
 */
public class RodonLogSimulator implements Runnable {

    //Clist is LogMaster that safe from conflict when read and write. 
    public static CopyOnWriteArrayList<RadonLog> cList = new CopyOnWriteArrayList<>();
    private Thread t;
    private String threadName;

    static {
        System.out.println("---__--_-_-");
        // Create list of customers
    }

    public RodonLogSimulator() {
        System.out.println("new log list");
        threadName = "test it";
    }

    public static CopyOnWriteArrayList<RadonLog> getInstance() {
        return cList;
    }

    public static void testList() {
        CopyOnWriteArrayList<RadonLog> list = RodonLogSimulator.getInstance();
        list.stream().forEach(i -> System.out.println(i));
        String cString= list.stream().map(c -> c.toString()).collect(Collectors.joining("\n"));
        System.out.println(cString);
    }

    public static void main(String[] args) {
        RodonLogSimulator.testList();
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            Random rand = new Random();
            while (true) {
                int i = 1;
                System.out.println("Thread: ------" + threadName + ", " + i);
                //cretae new LogMaster and add to cList
                cList.add(new RadonLog.LogMasterBuilder().setDateTime(new Date())
                        .setRadon(rand.nextInt(2000000 + 1))
                        .setHum(rand.nextInt(100 + 1))
                        .setTemp(rand.nextInt(40 + 1))
                        .setPress(rand.nextInt(300 + 1) + 700)
                        .build());
                i++;
                // Let the thread sleep for a while.
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
    
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
