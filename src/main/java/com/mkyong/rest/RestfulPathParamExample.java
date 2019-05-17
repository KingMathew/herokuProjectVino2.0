/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

/**
 * https://avaldes.com/restful-web-services-pathparam-example-using-jax-rs-and-jersey/
 * @author fabian.giraldo
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/calculate")
public class RestfulPathParamExample {
  
  
  @GET
  @Path("/squareroot/{value}")
  @Produces(MediaType.TEXT_HTML)
  public Response calcSqrt(@PathParam("value")  int value) {

    double answer = Math.sqrt(value);
    String msg = String.format("calcSqrt==> value: %d, answer: %10.4f", value, answer);
   
    
    return Response.status(200).entity(msg).build();
  }
  
  @GET
  @Path("/add/{value1}/{value2}")
  @Produces(MediaType.TEXT_HTML)
  public Response calcAddTwoValues(@PathParam("value1")  double value1, 
                                   @PathParam("value2")  double value2) {

    double answer = value1 + value2;
    String msg = String.format("calcAddTwoValues==> value1: %10.4f, value2: %10.4f, answer: %10.4f", 
                  value1, value2, answer);
   
    
    return Response.status(200).entity(msg).build();
  }
  
  @GET
  @Path("/subtract/{value1}/{value2}")
  @Produces(MediaType.TEXT_HTML)
  public Response calcSubTwoValues(@PathParam("value1")  double value1, 
                                   @PathParam("value2")  double value2) {

    double answer = value1 - value2;
    String msg = String.format("calcSubTwoValues==> value1: %10.4f, value2: %10.4f, answer: %10.4f", 
                  value1, value2, answer);
    
    
    return Response.status(200).entity(msg).build();
  }
}