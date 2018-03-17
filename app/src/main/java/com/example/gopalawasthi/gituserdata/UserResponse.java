package com.example.gopalawasthi.gituserdata;

/**
 * Created by Gopal Awasthi on 17-03-2018.
 */

public class UserResponse {

    String name ;
    String location ;
    String avatar_url ;
    String followers ;
    String  following;

    public UserResponse(String name, String location, String avatar_url, String followers, String following) {
        this.name = name;
        this.location = location;
        this.avatar_url = avatar_url;
        this.followers = followers;
        this.following = following;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
