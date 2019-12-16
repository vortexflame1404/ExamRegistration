package com.team4.progex;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("modify/")
public class Modify {
    @POST
    @Path("module/{module_id}/action")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response doPost(@PathParam("module_id") int module_id,
                           @FormParam("m_name") String m_name,
                           @FormParam("code") String code,
                           @FormParam("semester_id") int semester_id) {
        Methods.modify_module(module_id, m_name, code, semester_id);
        return Response.seeOther(URI.create("/all_module_assistant.html")).build();
    }
}