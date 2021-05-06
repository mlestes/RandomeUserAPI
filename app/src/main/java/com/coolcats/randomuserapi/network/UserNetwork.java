package com.coolcats.randomuserapi.network;

import com.coolcats.randomuserapi.mod.UserList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UserNetwork {

    private UserService userService = createRetrofit().create(UserService.class);

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<UserList> search(int count){
        return userService.searchUserList(count);
    }

    interface UserService {
        @GET("api")
        Call<UserList> searchUserList(@Query("results") int count);
    }
}
