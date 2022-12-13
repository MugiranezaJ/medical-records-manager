package com.mrm;

import com.mrm.data.DataStore;
import com.mrm.helpers.Response;
import com.mrm.models.UsersListModel;

import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersServlet extends HttpServlet {
    Gson gson = null;
    List<Object> usersList;
    Response responseBody;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        responseBody = new Response();
        String token = request.getHeader("Authorization");

        try {
            if (token == null) {
                responseBody.setMessage("No token provided");
                response.setStatus(401);
            } else {
                // check if requesting user is admin
                if (!DataStore.isAdmin(token)) {
                    responseBody.setMessage("You dont have permission to view users");
                    responseBody.setStatusCode(400);
                }else {
                    gson = new Gson();
                    usersList = new ArrayList<>();
                    Object[] users = DataStore.users.values().toArray();

                    // return only required fields according to UsersListModel model
                    for(Object user : users){
                        usersList.add(gson.fromJson(gson.toJson(user), UsersListModel.class));
                    }
                    responseBody.setData(gson.toJson(usersList));
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
        response.getWriter().append("This route is only supported for GET ")
                .append(request.getServletPath());
    }
}
