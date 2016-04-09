/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest;

import java.util.*;
import java.util.concurrent.*;
import java.math.*;

class test {

    public final static void main(String args[]) {
        List list = new ArrayList();
        list.add("vivek");
        list.add("kumar");
        Iterator i = list.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
            list.add("abhishek");
        }
    }
}
