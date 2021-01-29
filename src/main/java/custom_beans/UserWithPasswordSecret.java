/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom_beans;

import beans.User;

/**
 *
 * @author User
 */
public class UserWithPasswordSecret {
    private User user;
    private String passwordSecret;

    

    public String getPasswordSecret() {
        return passwordSecret;
    }

    public void setPasswordSecret(String passwordSecret) {
        this.passwordSecret = passwordSecret;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
