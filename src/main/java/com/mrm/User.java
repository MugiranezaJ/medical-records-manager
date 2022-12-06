package com.mrm;

import java.util.*;
import com.google.gson.JsonObject;
import com.mrm.helpers.Response;

public abstract class User {

    public static LinkedHashMap<String,JsonObject> dataStore = new LinkedHashMap<String, JsonObject>();
    public void setDataStore(JsonObject userData){
        dataStore.put(userData.get("email").toString(), userData);
    }
    public LinkedHashMap getDataStore(){
        return dataStore;
    }
    public Boolean userExists(String email){
        if(dataStore.get(email) != null)
            return true;
        return false;
    }

    public Response login(String email, String password) {
        Response response = new Response();
        if(dataStore.containsKey(email)){
            JsonObject user = dataStore.get(email);

            String storePassword = user.get("password").toString();
            if(!storePassword.equals(password)){
                response.setMessage("invalid login credentials, try again");
                response.setStatusCode(400);
                return response;
            }
            response.setMessage("logged in successfully");
            response.setStatusCode(200);
            response.setData(user.toString());
            return response;
        }
        response.setMessage("this user does not have an account");
        response.setStatusCode(404);
        return response;
    }

    public abstract Response signup(JsonObject user);
}
