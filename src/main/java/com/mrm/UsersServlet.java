package com.mrm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mrm.helpers.HttpRequestHelper;
import com.mrm.helpers.Response;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

// TODO: work on routes
@WebServlet(name = "UsersServlet", value = "/UsersServlet")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response res = new Response();
        String token = request.getHeader("authorization");
        System.out.println(token);

        try {
            if (token == null) {
                res.setMessage("no token provided");
                response.setStatus(401);
            } else {
                Admin user = new Admin();
                if (!user.isAdmin(token)) {
                    System.out.println(token);
                    res.setMessage("you dont have permission to view users");
                    res.setStatusCode(400);
                }else {
                    Gson gson = new Gson();
                    String d = gson.toJson(Admin.dataStore);
                    res.setData(d);
                    res.setMessage("users retrieved successfully");
                    response.setStatus(200);
                }
            }
            res.returnResponse(response, res);
        }catch (Exception e){
            e.printStackTrace();
            res.setMessage("an occurred while retrieving users");
            res.setStatusCode(500);
            res.returnResponse(response, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
