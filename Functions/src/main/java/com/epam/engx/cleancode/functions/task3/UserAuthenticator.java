package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {

    private SessionManager sessionManager;

    public void login(String userName) {
        sessionManager.setCurrentUser(getUserByName(userName));
    }

    public boolean isPasswordCorrect(String userName, String password){
        return isPasswordCorrect(getUserByName(userName), password);
    }
}
