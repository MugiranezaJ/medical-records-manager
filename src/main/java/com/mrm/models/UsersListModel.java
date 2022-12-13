package com.mrm.models;

public class UsersListModel {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String gender;
    public String age;
    public String role;

    public UsersListModel(
            String id,
            String firstName,
            String lastName,
            String email,
            String gender,
            String age,
            String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }
}
