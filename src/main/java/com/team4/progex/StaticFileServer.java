package com.team4.progex;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/static/{filename}")
public class StaticFileServer {

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @GET
    public Response doGet(@PathParam("filename") String filename){
        return Response.ok(classLoader.getResourceAsStream(filename)).build();
    }
}
