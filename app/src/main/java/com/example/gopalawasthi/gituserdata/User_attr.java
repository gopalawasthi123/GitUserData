package com.example.gopalawasthi.gituserdata;

/**
 * Created by Gopal Awasthi on 14-03-2018.
 */

public class User_attr {
    private String username;
    private int id;
    private String location;
    private String image;
    private String followersount;
    private String followingcount;

    public User_attr(String username, int id, String location, String image, String followersount, String followingcount) {
        this.username = username;
        this.id = id;
        this.location = location;
        this.image = image;
        this.followersount = followersount;
        this.followingcount = followingcount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFollowersount() {
        return followersount;
    }

    public void setFollowersount(String followersount) {
        this.followersount = followersount;
    }

    public String getFollowingcount() {
        return followingcount;
    }

    public void setFollowingcount(String followingcount) {
        this.followingcount = followingcount;
    }
}
