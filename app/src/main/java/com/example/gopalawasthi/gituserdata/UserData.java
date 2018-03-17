package com.example.gopalawasthi.gituserdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserData extends AppCompatActivity {
//    customAdapter adapter ;
    ListView listView;
//    ArrayList <User_attr> arrayList;
    ProgressBar progressBar;
    Button followersbutton;
    Button followingbutton;
    public static final String FOLLOWERS  =" followers";
    String urlstring;
     ArrayList<UserResponse> arrayList ;
RetrofitAdapter retrofitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        followersbutton = findViewById(R.id.followers);
        followingbutton = findViewById(R.id.following);
        progressBar = findViewById(R.id.progressbar);
//        adapter = new customAdapter(this,arrayList);
//        listView.setAdapter(adapter);
        retrofitAdapter =  new RetrofitAdapter(arrayList,this);
        listView.setAdapter(retrofitAdapter);
        Intent intent = getIntent();
       final String username =  intent.getStringExtra(MainActivity.USER_NAME);


//         urlstring = "https://api.github.com/users/"+ username;
//        listView.setVisibility(View.GONE);
//        progressBar.setVisibility(View.VISIBLE);
//        UserAsynchronous asynchronous = new UserAsynchronous(new UserAsynchronous.myinterface() {
//            @Override
//            public void ondownloadcomplete(ArrayList<User_attr> user_attrs) {
//                if(user_attrs != null){
//                    for(int i = 0 ; i < user_attrs.size() ; i++){
//                        User_attr userAttr = user_attrs.get(i);
//                    String username = userAttr.getUsername();
//                    String userlocation = userAttr.getLocation();
//                    String user_following =  userAttr.getFollowingcount();
//                    String user_follwers = userAttr.getFollowersount();
//                    String user_dp = userAttr.getImage();
//                    int user_id = userAttr.getId();
//                        Toast.makeText(UserData.this,userAttr.getUsername(), Toast.LENGTH_SHORT).show();
//                    User_attr myattr = new User_attr(username,user_id,userlocation,user_dp,user_follwers,user_following);
//                    arrayList.add(myattr);
//                    }
//                    adapter.notifyDataSetChanged();
//                }else
//                    Toast.makeText(UserData.this,"tryAgain!",Toast.LENGTH_SHORT).show();
//                listView.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//        asynchronous.execute(urlstring);
//
//
        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.github.com/users/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        MyInterface myInterface = retrofit.create( MyInterface.class);
        Call<UserResponse> call = myInterface.getUserResponse();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
              UserResponse userResponse =  response.body();
            arrayList.add(userResponse);
                retrofitAdapter.notifyDataSetChanged();
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(UserData.this, "tryagain!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void Followersclick (View view){

    Toast.makeText(this,"followersclicked", Toast.LENGTH_SHORT).show();
//    Intent intent = new Intent(this,FollowersList.class);
//    intent.putExtra(FOLLOWERS,urlstring);
//    startActivity(intent);


    }


    public void Followingclick (View view){

        Toast.makeText(this,"followingclicked",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,FollowingList.class);
//        intent.putExtra(FOLLOWERS,urlstring);
//        startActivity(intent);

    }

}
