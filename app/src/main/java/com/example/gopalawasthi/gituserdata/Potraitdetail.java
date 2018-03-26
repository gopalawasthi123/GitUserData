package com.example.gopalawasthi.gituserdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Potraitdetail extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potraitdetail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            userdetail userdetail = new userdetail();
            userdetail.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.container,userdetail).commit();

        }

    }
}
