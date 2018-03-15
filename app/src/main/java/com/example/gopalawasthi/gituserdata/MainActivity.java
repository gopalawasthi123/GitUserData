package com.example.gopalawasthi.gituserdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText ;
    Button searchbutton;
    public static final String USER_NAME ="myuser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        searchbutton = findViewById(R.id.Searchbutton);
        searchbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     Intent intent =  new Intent(MainActivity.this,UserData.class);
     String a = editText.getText().toString();
     intent.putExtra(USER_NAME,a);
     startActivity(intent);
    }
}
