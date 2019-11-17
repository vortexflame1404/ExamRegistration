package com.team4.progex;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;

@Path("/user/add-student")
public class AddNewStudent {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(@FormParam("pass") String password,
                           @FormParam("fname") String fname,
                           @FormParam("lname") String lname,
                           @FormParam("acc_login") String acc_login,
                           @FormParam("my_code") String code) throws SQLException {
//        if (password == null)
//            password = "default";
        Methods.insert_student(password, fname, lname, acc_login, code);
        return Response.seeOther(URI.create("/home_assistant")).build();
    }
}