package com.example.machachong.youdu.manager;

import com.example.machachong.youdu.module.user.User;

public class UserManager {
    private static UserManager userManager = null;

    private User mUser;

    public static UserManager getInstance() {
        if (userManager == null) {
            synchronized (UserManager.class) {
                if (userManager == null) {
                    userManager = new UserManager();
                }
            }
        }
        return userManager;
    }

    public void setUser(User user){
        mUser = user;
    }

    public User getmUser(){
        return mUser;
    }

    public boolean hasLoigin(){
        return !(mUser == null) ;
    }

}
