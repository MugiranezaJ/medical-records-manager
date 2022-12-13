package com.mrm.validation;

import com.mrm.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {
    List<String> errors;
    public List<String> validateSignup(UserModel userModel){
        errors = new ArrayList<String>();
        if(userModel.firstName == null || userModel.firstName.equals("") ){
            errors.add("Fist name is required");
        }
        if(userModel.lastName == null || userModel.lastName.equals("")){
            errors.add("Last name is required");
        }
        if(userModel.email == null || userModel.email.equals("")){
            errors.add("Email is required");
        }
        if(userModel.password == null || userModel.password.equals("")){
            errors.add("Password is required");
        }
        if(userModel.role == null || userModel.role.equals("")){
            errors.add("Role is required");
        }
        if(userModel.age == null || userModel.age.equals("")){
            errors.add("Age is required");
        }
        if(userModel.gender == null || userModel.gender.equals("")){
            errors.add("Gender is required");
        }
        return errors;
    }
}
