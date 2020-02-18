package com.tomaslerichedemo.AppApiUsers.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

    @NotNull(message = "Username must not be null")
    @Size(min = 3, max =  18)
    private String username;
    @NotNull(message = "Email must not be null")
    @Email
    private String email;
    @NotNull(message = "Password must not be null")
    @Size(min = 6, max =  18, message = "Password must be between 6 and 18 characters")
    private String password;

    public CreateUserRequestModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public CreateUserRequestModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
