package com.mrm.models;

public class UserModel {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String gender;
    public String age;
    public String role;

    public UserModel(
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String gender,
            String age,
            String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }
}
