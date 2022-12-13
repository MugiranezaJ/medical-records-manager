package com.mrm.user;

import com.google.gson.Gson;
import com.mrm.data.DataStore;
import com.mrm.helpers.Response;
import com.mrm.models.UserModel;

public abstract class User {

    Gson gson = null;
    Response response = null;

    public Response login(String email, String password) {
        response = new Response();
        if(DataStore.users.containsKey(email)){
            UserModel user = DataStore.users.get(email);
            gson = new Gson();

            if(!user.password.equals(password)){
                response.setMessage("Invalid login credentials, try again");
                response.setStatusCode(400);
                return response;
            }
            response.setMessage("Logged in successfully");
            response.setStatusCode(200);
            response.setData(gson.toJson(user));
            return response;
        }
        response.setMessage("This user does not have an account");
        response.setStatusCode(404);
        return response;
    }

    public abstract Response signup(UserModel user);
}
