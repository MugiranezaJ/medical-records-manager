package com.mrm;

import com.google.gson.Gson;
import com.mrm.helpers.Response;
import com.mrm.user.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Response responseBody = new Response();
        String token = request.getHeader("Authorization");

        try {
            if (token == null) {
                responseBody.setMessage("No token provided");
                response.setStatus(401);
            } else {
                Admin user = new Admin();
                if (!user.isAdmin(token)) {
                    System.out.println(token);
                    responseBody.setMessage("You dont have permission to view users");
                    responseBody.setStatusCode(400);
                }else {
                    Gson gson = new Gson();
                    String d = gson.toJson(Admin.dataStore);
                    responseBody.setData(d);
                    responseBody.setMessage("Users retrieved successfully");
                    response.setStatus(200);
                }
            }
            responseBody.returnResponse(response, responseBody);
        }catch (Exception e){
            e.printStackTrace();
            responseBody.setMessage("An occurred while retrieving users");
            responseBody.setStatusCode(500);
            responseBody.returnResponse(response, responseBody);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
