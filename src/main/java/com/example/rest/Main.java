/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

/**
 * Main class
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
        path = "myapp";
        BASE_URI = protocol + host.orElse("localhost") + ":" + port.orElse("80") + "/" + path + "/";
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
        final ResourceConfig rc = new ResourceConfig().packages("com.example.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        LogsList R1 = new LogsList();
        R1.start();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
