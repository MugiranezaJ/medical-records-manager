package com.mrm.user;

import com.google.gson.Gson;
import com.mrm.data.DataStore;
import com.mrm.helpers.Response;
import com.mrm.models.UserModel;

public class Admin extends User{
    @Override
    public Response signup(UserModel userData) {

        response = new Response();
        gson = new Gson();
        // check password length for admin
        if(userData.password.length() != 10){
            response.setStatusCode(400);
            response.setMessage("Password must be 10 characters long");
            return response;
        }

        // save the user
        DataStore.addUser(userData);

        // return response
        response.setStatusCode(200);
        response.setMessage("Admin created successfully");
        response.setData(gson.toJson(userData));
        return response;
    }

    public Response getUsers(){
        response = new Response();
        response.setData(DataStore.users.toString());
        response.setMessage("All users retrieved successfully");
        response.setStatusCode(200);
        return response;
    }
}
