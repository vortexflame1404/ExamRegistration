package com.team4.progex;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("delete/")
public class Delete {
    @POST
    @Path("module/{id}/action")
    @Produces()
    public Response doPost(@PathParam("id") int id) {
        Methods.cancel_module(id);
        return Response.seeOther(URI.create("all_module_assistant.html")).build();
    }
}
