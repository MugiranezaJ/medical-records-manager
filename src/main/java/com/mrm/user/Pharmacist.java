package com.mrm.user;

import com.google.gson.Gson;
import com.mrm.data.DataStore;
import com.mrm.helpers.Response;
import com.mrm.models.UserModel;

public class Pharmacist extends User{
    @Override
    public Response signup(UserModel userData) {
        response = new Response();
        gson = new Gson();

        // check password length for pharmacist
        if(userData.password.length() != 4){
            response.setStatusCode(400);
            response.setMessage("Password must be 4 characters long");
            return response;
        }

        // save the user
        DataStore.addUser(userData);

        // return response
        response.setStatusCode(200);
        response.setMessage("Pharmacist created successfully");
        response.setData(gson.toJson(userData));
        return response;
    }
}
