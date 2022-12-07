package com.mrm;

import com.google.gson.JsonObject;
import com.mrm.helpers.HttpRequestHelper;
import com.mrm.helpers.Response;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject obj = HttpRequestHelper.getParamsFromPost(request);
        Response res = new Response();
        try {
            String email = obj.get("email").getAsString();
            String password = obj.get("password").getAsString();
            if (email.isEmpty()) {
                res.setMessage("email is required");
                res.setStatusCode(400);
            } else if (password.isEmpty()) {
                res.setMessage("password is required");
                res.setStatusCode(400);
            } else {
                Admin admin = new Admin();
                res = admin.login(email, password);
            }

            response.setStatus(res.getStatusCode());
            res.returnResponse(response, res);
        }catch (NullPointerException e){
            res.setMessage("both email and password are required");
            res.setStatusCode(400);
            res.returnResponse(response, res);
        }catch (Exception e){
            e.printStackTrace();
            res.setStatusCode(500);
            res.setMessage("An error occurred while trying to log you in");
            res.returnResponse(response, res);
        }
    }
}
