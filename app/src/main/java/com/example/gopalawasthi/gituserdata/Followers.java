package com.example.gopalawasthi.gituserdata;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gopal Awasthi on 15-03-2018.
 */

public class Followers {
    @SerializedName("login")
   private String name;
   private String avatar_url;

    public Followers(String name, String avatar_url) {
        this.name = name;
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
