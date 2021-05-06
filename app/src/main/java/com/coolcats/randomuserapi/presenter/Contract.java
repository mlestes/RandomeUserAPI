package com.coolcats.randomuserapi.presenter;

import com.coolcats.randomuserapi.mod.User;
import com.coolcats.randomuserapi.util.Status;

import java.util.List;

public interface Contract {

    interface Presenter {
        void getUsers(int count);
    }

    interface View {
        void showUsers(List<User> userList);
        void showError(String message);
        void setStatus(Status status);
    }

}
