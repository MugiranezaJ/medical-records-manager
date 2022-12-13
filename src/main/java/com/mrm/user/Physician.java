package com.mrm.user;

import com.google.gson.Gson;
import com.mrm.data.DataStore;
import com.mrm.helpers.Response;
import com.mrm.models.UserModel;

public class Physician extends User{

    @Override
    public Response signup(UserModel userModel) {
        response = new Response();
        gson = new Gson();

        // check password length for physician
        if(userModel.password.length() != 8){
            response.setStatusCode(400);
            response.setMessage("password must be 8 characters long");
            return response;
        }

        // save the user
        DataStore.addUser(userModel);

        // return response
        response.setStatusCode(200);
        response.setMessage("Physician created successfully");
        response.setData(gson.toJson(userModel));
        return response;
    }
}
