package com.coolcats.randomuserapi.presenter;

import com.coolcats.randomuserapi.mod.User;
import com.coolcats.randomuserapi.mod.UserList;
import com.coolcats.randomuserapi.network.UserNetwork;
import com.coolcats.randomuserapi.util.Status;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements Contract.Presenter{

    private Contract.View view;
    private List<User> userList;

    private UserNetwork userNetwork = new UserNetwork();

    public UserPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getUsers(int count) {

        view.setStatus(Status.LOADING);
        new Thread() {
            @Override
            public void run() {
                super.run();
                userNetwork.search(count).enqueue(new Callback<UserList>() {
                    @Override
                    public void onResponse(Call<UserList> call, Response<UserList> response) {
                        UserList list = response.body();
                        if(list != null)
                            userList = list.getResults();
                        else {
                            view.setStatus(Status.ERROR);
                            return;
                        }
                        if(userList.size() > 0) {
                            view.showUsers(userList);
                            view.setStatus(Status.COMPLETED);
                        }
                        else {
                            view.setStatus(Status.ERROR);
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<UserList> call, Throwable t) {
                        view.setStatus(Status.ERROR);
                    }
                });
            }
        }.start();

    }
}
