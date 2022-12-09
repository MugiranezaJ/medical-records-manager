package com.mrm.user;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mrm.helpers.Response;

public abstract class User {

    public static LinkedHashMap<String,JsonObject> dataStore = new LinkedHashMap<String, JsonObject>();
    public void setDataStore(JsonObject userData){
        // Generate Token
        userData.getAsJsonObject().addProperty(
                "token",
                UUID.randomUUID().toString().replace("-", ""));
        dataStore.put(userData.get("email").getAsString(), userData);
    }

    public boolean isAdmin(String email){
        JsonObject user = dataStore.get(email);
        if(user == null ) return false;
        if(user.get("role").toString().contains("admin"))
            return true;
        return false;
    }

    public Response login(String email, String password) {
        Response response = new Response();
        if(dataStore.containsKey(email)){
            JsonObject user = dataStore.get(email);

            String storePassword = user.get("password").getAsString();
            if(!storePassword.equals(password)){
                response.setMessage("Invalid login credentials, try again");
                response.setStatusCode(400);
                return response;
            }
            response.setMessage("Logged in successfully");
            response.setStatusCode(200);
            response.setData(user.toString());
            return response;
        }
        response.setMessage("This user does not have an account");
        response.setStatusCode(404);
        return response;
    }

    public abstract Response signup(JsonObject user);
}
