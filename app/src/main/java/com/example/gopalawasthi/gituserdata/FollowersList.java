package com.example.gopalawasthi.gituserdata;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollowersList extends AppCompatActivity implements BlankFragment.forfollowerCallback  {
    // ListView listView;

    FrameLayout container ;
    boolean islandscapermode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_list);
//        listView = findViewById(R.id.followerslist);
        container = findViewById(R.id.container);
        if(container!=null){
            islandscapermode=true;
        }
    }

    @Override
    public void onfollowerselected(Followers followers) {
     Bundle bundle = new Bundle();
     bundle.putString("username",followers.getName());
     bundle.putString("avatar",followers.getAvatar_url());
     if(islandscapermode){

         Toast.makeText(this,"User slected" + followers.getName(),Toast.LENGTH_SHORT).show();
            userdetail fragment = new userdetail();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container,fragment).commit();

        }else {
            Intent intent = new Intent(this,Potraitdetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
         Toast.makeText(this, "potrait"+bundle.getString("username"), Toast.LENGTH_SHORT).show();

        }


    }
}