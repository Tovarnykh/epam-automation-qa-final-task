package com.epam.tovarnykh.ta.saucedemo.model;

import com.epam.tovarnykh.ta.driver.Browser;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String userName;
    private String password;
    private Browser browser;

    @JsonCreator
    public User(
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password,
            @JsonProperty("browser") Browser browser
    ) {
        this.userName = userName;
        this.password = password;
        this.browser = browser;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Browser getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", browser=" + browser +
                '}';
    }

}
