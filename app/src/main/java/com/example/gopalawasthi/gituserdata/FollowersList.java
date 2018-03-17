package com.example.gopalawasthi.gituserdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollowersList extends AppCompatActivity {
    ListView listView;
    ArrayList<Followers> arrayList;
    FollowersAdapter followersAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_list);
        listView = findViewById(R.id.followerslist);
        arrayList = new ArrayList<>();
        progressBar = findViewById(R.id.progressbar);
        followersAdapter = new FollowersAdapter(arrayList, this);
        listView.setAdapter(followersAdapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.USER_NAME);
//        String url = name  ;
        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
//        FollowersAsynchronous followersAsynchronous = new FollowersAsynchronous(new FollowersAsynchronous.followersinterface() {
//            @Override
//            public void onfollowerscomplete(ArrayList<Followers> followersArrayList) {
//                if(followersArrayList!=null){
//                    for (int i =0 ;i <followersArrayList.size() ;i++){
//                    Followers followers = followersArrayList.get(i);
//                        String name = followers.getName();
//                        String image = followers.getImage();
//                        Followers followers1  = new Followers(name , image);
//                        arrayList.add(followers1);
//
//                    }
//                    followersAdapter.notifyDataSetChanged();
//
//                } else
//                    Toast.makeText(FollowersList.this,"tryagain!",Toast.LENGTH_SHORT).show();
//                listView.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//        followersAsynchronous.execute(url);
//

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyInterface myInterface = retrofit.create(MyInterface.class);
        final Call<ArrayList<Followers>> arrayListCall = myInterface.getfollowers(name);
        arrayListCall.enqueue(new Callback<ArrayList<Followers>>() {
            @Override
            public void onResponse(Call<ArrayList<Followers>> call, Response<ArrayList<Followers>> response) {
                if(response!=null){
                    for(int i =0 ; i< response.body().size() ; i++){
                        arrayList.add(response.body().get(i));
                    }
                  followersAdapter.notifyDataSetChanged();
                    listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Followers>> call, Throwable t) {

            }
        });
    }
}