/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
package com.jeus.radon.service;

import com.jeus.radon.database.Connection;
import java.io.FileInputStream;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;
import java.util.Properties;

/**
 * Main class for run services to chart . this class show services on chart
 * sample. have to use "sudo" command to run this file.
 *
 * @author jeus
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI;
    public static final String protocol;
    public static final Optional<String> host;
    public static final String path;
    public static final Optional<String> port;

    static {
        protocol = "http://";
        host = Optional.ofNullable(System.getenv("HOSTNAME"));
        port = Optional.ofNullable(System.getenv("PORT"));
        path = "crms";
        BASE_URI = protocol + host.orElse("0.0.0.0") + ":" + port.orElse("8076") + "/" + path + "/";
    }
  
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages("com.jeus.radon.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "/etc/radon/config/radonService.cfg";
            input = new FileInputStream(filename);
            if (input == null) {
                System.err.print("Sorry, unable to find " + filename);
                return;
            }
            prop.load(input);
            Connection.db_url = prop.getProperty("db-url");
            Connection.jdbc_driver = prop.getProperty("jdbc-driver");
            Connection.username = prop.getProperty("username");
            Connection.password = prop.getProperty("password");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        RodonLogSimulator R1 = new RodonLogSimulator();
//        R1.start();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
