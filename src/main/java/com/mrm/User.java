package com.mrm;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mrm.helpers.Response;

public abstract class User {

    public static LinkedHashMap<String,JsonObject> dataStore = new LinkedHashMap<String, JsonObject>();
    public void setDataStore(JsonObject userData){
        dataStore.put(userData.get("email").getAsString(), userData);
    }
    public LinkedHashMap getDataStore(){
        return dataStore;
    }
    public Boolean userExists(String email){
        if(dataStore.get(email) != null)
            return true;
        return false;
    }

    public String getDataStoreJson(){
        Gson gson = new Gson();
        String dataStoreJson = gson.toJson(dataStore);
        return dataStoreJson;
    }
    public boolean isAdmin(String email){
        JsonObject user = dataStore.get(email);
        System.out.println("===============================");
        String users = getDataStoreJson();

        System.out.println(getDataStoreJson());
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
