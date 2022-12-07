package com.mrm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mrm.helpers.HttpRequestHelper;
import com.mrm.helpers.Response;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

//@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {
//    public static  String
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hello
//        UserServlet user = new UserServlet(1, "Jackson", 22);
//        String userJsonString = new Gson().toJson(user);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(userJsonString);
//        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response res = new Response();

        try {
            JsonObject obj = HttpRequestHelper.getParamsFromPost(request);
            String role = obj.get("role").toString().replace("\"", "");

            if(Objects.equals(role, "admin")){
                Admin admin = new Admin();
                res = admin.signup(obj);

            }else if(Objects.equals(role, "pharmacist")){
                Pharmacist pharmacist = new Pharmacist();
                res = pharmacist.signup(obj);

            }else if(Objects.equals(role, "patient")){
                Patient patient = new Patient();
                res = patient.signup(obj);

            }else if(Objects.equals(role, "physician")){
                Physician physician = new Physician();
                res = physician.signup(obj);

            }else{
                res.setStatusCode(400);
                res.setMessage("invalid role");
            }

            response.setStatus(res.getStatusCode());
            res.returnResponse(response, res);

        }catch (Exception e){
            response.setStatus(500);
            res.setMessage("an error occurred while signing up");
            res.returnResponse(response, res);
        }
    }
}
