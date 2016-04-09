/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest;

/**
 *
 * @author jeus
 */
import java.util.*;
import java.util.concurrent.*;
import java.math.*;

class test1 {

    public final static void main(String args[]) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        list.add("vivek");
        list.add("kumar");
        Iterator i = list.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
            list.add("abhishek");
        }
        System.out.println("After modification:");
        Iterator i2 = list.iterator();
        while (i2.hasNext()) {
            System.out.println(i2.next()+"------<<<2");
        }
    }
}
