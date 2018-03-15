package com.example.gopalawasthi.gituserdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class FollowingList extends AppCompatActivity {

    ListView listView ;
    ArrayList<Followers> arrayList;
    FollowersAdapter followersAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_list);
        listView = findViewById(R.id.followinglist);
        arrayList = new ArrayList<>();
        progressBar = findViewById(R.id.progressbar);
        followersAdapter= new FollowersAdapter(arrayList,this);
        listView.setAdapter(followersAdapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra(UserData.FOLLOWERS);
        String url = name + "/" + "following";

        progressBar.setVisibility(View.VISIBLE);
        FollowersAsynchronous followersAsynchronous = new FollowersAsynchronous(new FollowersAsynchronous.followersinterface() {
            @Override
            public void onfollowerscomplete(ArrayList<Followers> followersArrayList) {
                if(followersArrayList!=null){
                    for (int i =0 ;i <followersArrayList.size() ;i++){
                        Followers followers = followersArrayList.get(i);
                        String name = followers.getName();
                        String image = followers.getImage();
                        Followers followers1  = new Followers(name , image);
                        arrayList.add(followers1);

                    }
                    followersAdapter.notifyDataSetChanged();

                } else
                    Toast.makeText(FollowingList.this,"tryagain!",Toast.LENGTH_SHORT).show();
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        followersAsynchronous.execute(url);



    }


}
