package com.example.gopalawasthi.gituserdata;

/**
 * Created by Gopal Awasthi on 15-03-2018.
 */

public class Followers {
    String name;
    String image;

    public Followers(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
