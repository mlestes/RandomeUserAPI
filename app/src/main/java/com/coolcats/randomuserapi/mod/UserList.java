
package com.coolcats.randomuserapi.mod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {

    @SerializedName("results")
    @Expose
    private List<User> users = null;

    public List<User> getResults() {
        return users;
    }

    public void setResults(List<User> users) {
        this.users = users;
    }

}
