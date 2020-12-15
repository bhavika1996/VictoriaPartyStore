package com.example.groupprojectandroid;

import java.util.HashMap;

public class UserDetailsSingleton {

    private static UserDetailsSingleton userDetailsSingleton = null;
    public HashMap<String, String> userDetails;

    private UserDetailsSingleton() {

        userDetails = new HashMap<>();
    }

    public static UserDetailsSingleton getInstance() {

        if (userDetailsSingleton == null) {

            userDetailsSingleton = new UserDetailsSingleton();
        }
        return userDetailsSingleton;
    }
}
