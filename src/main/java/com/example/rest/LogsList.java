/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.rest;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class LogsList implements Runnable {

    public static CopyOnWriteArrayList<LogMaster> cList = new CopyOnWriteArrayList<>();
    private Thread t;
    private String threadName;

    static {
        System.out.println("---__--_-_-");
        // Create list of customers
    }

    public LogsList() {
        System.out.println("new log list");
        threadName = "test it";
    }

    public static CopyOnWriteArrayList<LogMaster> getInstance() {
        return cList;
    }

    public static void testList() {
        CopyOnWriteArrayList<LogMaster> list = LogsList.getInstance();
        list.stream()
                .forEach(i -> System.out.println(i));
        String cString
                = list.stream()
                .map(c -> c.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(cString);
    }

    public static void main(String[] args) {
        LogsList.testList();
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            Random rand = new Random();
            while (true) {
                int i = 4;
//         for(int i = 4; i > 0; i--) {
                System.out.println("Thread: ------" + threadName + ", " + i);
                // Let the thread sleep for a while.

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                cList.add(
                        new LogMaster.LogMasterBuilder().dateTime(new Date())
                        .radon(rand.nextInt(2000000 + 1))
                        .hum(rand.nextInt(100 + 1))
                        .temp(rand.nextInt(40 + 1))
                        .press(rand.nextInt(300 + 1) + 700)
                        .build()
                );
                i++;
                Thread.sleep(5000);
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
