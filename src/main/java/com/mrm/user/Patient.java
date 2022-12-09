package com.mrm.user;

import com.google.gson.JsonObject;
import com.mrm.helpers.Response;

import java.util.UUID;


public class Patient extends User{

    @Override
    public Response signup(JsonObject userData) {
        Response response = new Response();
        try{
            String password = userData.get("password").getAsString();

            // check length password
            if(password.length() != 6){
                response.setStatusCode(400);
                response.setMessage("password must be 6 characters long");
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
}
