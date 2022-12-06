package com.mrm;

import com.google.gson.JsonObject;
import com.mrm.helpers.HttpRequestHelper;
import com.mrm.helpers.Response;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject obj = HttpRequestHelper.getParamsFromPost(request);
        String email = String.valueOf(obj.get("email"));
        String password = String.valueOf(obj.get("password"));
        Response res = new Response();
        try {
            if (email.contains("null")) {
                res.setMessage("email is required");
                res.setStatusCode(400);
            }
            else if (password.contains("null")) {
                res.setMessage("password is required");
                res.setStatusCode(400);
            }else{
                Admin admin = new Admin();
                res = admin.login(email, password);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(res.getStatusCode());

            PrintWriter out = response.getWriter();
            out.print(res.getData());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
