package com.mrm;

import com.google.gson.JsonObject;
import com.mrm.helpers.Request;
import com.mrm.helpers.Response;
import com.mrm.user.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JsonObject obj = Request.getParamsFromPost(request);
        Response responseBody = new Response();

        try {
            String email = obj.get("email").getAsString();
            String password = obj.get("password").getAsString();
            if (email.isEmpty()) {
                responseBody.setMessage("Email is required");
                responseBody.setStatusCode(400);
            } else if (password.isEmpty()) {
                responseBody.setMessage("Password is required");
                responseBody.setStatusCode(400);
            } else {
                Admin admin = new Admin();
                responseBody = admin.login(email, password);
            }

            response.setStatus(responseBody.getStatusCode());
            responseBody.returnResponse(response, responseBody);
        }catch (NullPointerException e){
            responseBody.setMessage("Both email and password are required");
            responseBody.setStatusCode(400);
            responseBody.returnResponse(response, responseBody);
        }catch (Exception e){
            e.printStackTrace();
            responseBody.setStatusCode(500);
            responseBody.setMessage("An error occurred while trying to log you in");
            responseBody.returnResponse(response, responseBody);
        }
    }
}
