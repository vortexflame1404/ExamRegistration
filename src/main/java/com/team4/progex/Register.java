package com.team4.progex;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("register/")
public class Register {
    @POST
    @Path("module/{module_id}/{student_id}/{exam_id}/{re_date}/{e_date}/action")
    @Produces()
    public Response doPost(@PathParam("student_id") int student_id,
                           @PathParam("exam_id") int exam_id,
                           @PathParam("re_date") String re_date,
                           @PathParam("e_date") String e_date,
                           @PathParam("module_id") String module_id) {
//        if (password == null)
//            password = "default";
        Methods.register_exam(re_date, e_date, student_id, exam_id);
        return Response.seeOther(URI.create("/all_modules/view/view_exams/" + module_id)).build();
    }
}
