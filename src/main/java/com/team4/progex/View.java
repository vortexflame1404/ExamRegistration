package com.team4.progex;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/view/")
public class View {

    @GET
    @Path("module/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListModule(@PathParam("id")int id) {
        return Methods.list_module_student_has_enrolled_in(id).toString();
    }

    @GET
    @Path("home/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfo(@PathParam("id") int id) {
        return Methods.list_account_by_id(id).toString();
    }
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postListModule(@PathParam("id")int id) {
//        return Response.ok(Methods.list_module_student_has_enrolled_in(id).toString()).build();
//    }

    @GET
    @Path("session/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSession(@PathParam("id")int id) {
        return Methods.list_sessions_in_module(id).toString();
    }

    @GET
    @Path("view_all_exams/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllExams(@PathParam("id") int id) {
        return Methods.list_exam_that_student_has_registered(id).toString();
    }

    @GET
    @Path("modules")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllModules() {
        return Methods.list_module().toString();
    }

    @GET
    @Path("all_modules/view_exams/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllModules(@PathParam("id") int id) {
        return Methods.list_exam_from_module(id).toString();
    }

    @GET
    @Path("all_account")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAccount() {
        return Methods.list_account().toString();
    }
}
