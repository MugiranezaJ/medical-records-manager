package com.mrm.data;

import com.mrm.models.UserModel;

import java.util.LinkedHashMap;

public class DataStore {
    public static LinkedHashMap<String, UserModel> users = new LinkedHashMap<String, UserModel>();

    public static void addUser(UserModel userData){
        users.put(userData.email, userData);
    }

    public static boolean isAdmin(String email){
        UserModel user = DataStore.users.get(email);
        if(user == null ) return false;
        if(user.role.equalsIgnoreCase("admin"))
            return true;
        return false;
    }

}
