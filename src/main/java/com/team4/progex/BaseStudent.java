package com.team4.progex;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class BaseStudent {

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHomePage(){
        String fileName = "home.html";
        return Response.ok(classLoader.getResourceAsStream(fileName)).build();
    }

    @GET
    @Path("home_assistant.html")
    @Produces(MediaType.TEXT_HTML)
    public Response getHomePageAssistant(){
        String fileName = "home_assistant.html";
        return Response.ok(classLoader.getResourceAsStream(fileName)).build();
    }

    @GET
    @Path("all_modules_student.html")
    @Produces(MediaType.TEXT_HTML)
    public Response getAllModulePage() {
        String filename = "all_modules_student.html";
        return Response.ok(classLoader.getResourceAsStream(filename)).build();
    }

    @GET
    @Path("add_student.html")
    @Produces(MediaType.TEXT_HTML)
    public Response getAddStudent() {
        String filename = "add_student.html";
        return Response.ok(classLoader.getResourceAsStream(filename)).build();
    }

    @GET
    @Path("all_module_assistant.html")
    @Produces(MediaType.TEXT_HTML)
    public Response getModule() {
        String filename = "all_module_assistant.html";
        return Response.ok(classLoader.getResourceAsStream(filename)).build();
    }

    @GET
    @Path("/all_modules/view/view_exams/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response getExamOfModule() {
        String filename = "view_exams.html";
        return Response.ok(classLoader.getResourceAsStream(filename)).build();
    }



    @GET
    @Path("view_all_exams.html")
    @Produces(MediaType.TEXT_HTML)
    public Response getStudentExam() {
        String filename = "view_all_exams.html";
        return Response.ok(classLoader.getResourceAsStream(filename)).build();

    }

    @GET
    @Path("all_account.html")
    @Produces(MediaType.TEXT_HTML)
    public Response getAllAccount() {
        String filename = "all_account.html";
        return Response.ok(classLoader.getResourceAsStream(filename)).build();

    }
}
