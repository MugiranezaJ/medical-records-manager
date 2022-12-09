package com.mrm.user;

import com.google.gson.JsonObject;
import com.mrm.helpers.Response;

import java.util.UUID;

public class Admin extends User{
    @Override
    public Response signup(JsonObject userData) {

        Response response = new Response();
        try{
            // check length password
            String password = userData.get("password").getAsString();
            if(password.length() != 10){
                response.setStatusCode(400);
                response.setMessage("password must be 10 characters long");
                return response;
            }

            // save the user
            setDataStore(userData);

            // return response
            response.setStatusCode(200);
            response.setMessage("user created successfully");
            response.setData(userData.toString());
            return response;

        }catch (Exception e){
            e.printStackTrace();
            response.setStatusCode(500);
            response.setMessage("there was error creating a user");
            return response;
        }
    }

    public Response getUsers(){
        Response response = new Response();
        response.setData(dataStore.toString());
        response.setMessage("users retrieved successfully");
        response.setStatusCode(200);
        return response;
    }
}
