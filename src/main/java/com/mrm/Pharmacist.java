package com.mrm;

import com.google.gson.JsonObject;
import com.mrm.helpers.Response;

import java.util.UUID;

public class Pharmacist extends User{
    @Override
    public Response signup(JsonObject userData) {
        Response response = new Response();
        try{
            String email = userData.get("email").toString();
            String password = userData.get("password").toString().replace("\"", "");

            // check if user exists
            if(userExists(email)){
                response.setStatusCode(409);
                response.setMessage("user already exists");
                return response;
            }

            // check length password
            if(password.length() != 4){
                response.setStatusCode(400);
                response.setMessage("password must be 4 characters long");
                return response;
            }

            // save the user
            userData.getAsJsonObject().addProperty("token", UUID.randomUUID().toString().replace("-", ""));
            setDataStore(userData);

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
