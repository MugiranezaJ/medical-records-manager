package com.mrm.user;

import com.google.gson.Gson;
import com.mrm.data.DataStore;
import com.mrm.helpers.Response;
import com.mrm.models.UserModel;


public class Patient extends User{

    @Override
    public Response signup(UserModel userData) {
        response = new Response();
        gson = new Gson();

        // check password length patient
        if(userData.password.length() != 6){
            response.setStatusCode(400);
            response.setMessage("password must be 6 characters long");
            return response;
        }

        // save the user
        DataStore.addUser(userData);

        // return response
        response.setStatusCode(200);
        response.setMessage("user created successfully");
        response.setData(gson.toJson(userData));
        return response;
    }
}
