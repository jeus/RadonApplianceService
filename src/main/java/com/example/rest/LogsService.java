/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */

package com.example.rest;

import static com.example.rest.LogsList.cList;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
public class LogsService {


  @GET
  @Path("/all")
  @Produces(MediaType.TEXT_PLAIN)
  public String getAllCustomers() {
      System.out.println("---------------------------INJAaaaa----------------------------------"+cList.size());
      return "---Customer List---\n" + cList.stream().map(c -> c.toString()).collect(Collectors.joining("\n"));
  }
@GET
@Path("/chrt")
@Produces(MediaType.APPLICATION_JSON)
public String getJsonLogs()
{
    return "?({\n"+
    "\"xData\": ["+cList.stream().map(c -> c.getDateTime().getTime()+"").collect(Collectors.joining(","))+"],\n"+
    "datasets: [{\n"+
        "\"name\" : \"Speed\","+
        "\"data\": ["+cList.stream().map(c -> c.getRadon()+"").collect(Collectors.joining(","))+"],\n"+
        "\"unit\": \"km/h\",\n"+
        "\"type\": \"line\",\n"+
        "\"valueDecimals\": 1\n"+
    "}, {\n"+
        "\"name\": \"Elevation\",\n"+
        "\"data\": ["+cList.stream().map(c -> c.getHum()+"").collect(Collectors.joining(","))+"],\n"+
        "\"unit\": \"m\",\n"+
        "\"type\": \"area\",\n"+
        "\"valueDecimals\": 0\n"+
    "}, {\n"+
        "\"name\": \"Heart rate\",\n"+
        "\"data\": ["+cList.stream().map(c -> c.getTemp()+"").collect(Collectors.joining(","))+"],\n"+
        "\"unit\": \"bpm\",\n"+
        "\"type\": \"area\",\n"+
        "\"valueDecimals\": 0\n"+
    "}]\n"+
"});\n";
}
  
  @GET
  @Path("{date}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCustomer(@PathParam("dateTime") long dateTime) {
      System.out.println("------------------------INNNNNJA-----------------------------");
      Optional<LogMaster> match;
      match = cList.stream().filter(c -> c.getDateTime().getTime() == dateTime).findFirst();
    
    if (match.isPresent()) {
      return "---Customer---\n" + match.get().toString();
    } else {
      return "Customer not found";
    }
  }
}
