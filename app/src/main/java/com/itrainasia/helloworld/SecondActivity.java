package com.itrainasia.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
       
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String cell = intent.getStringExtra("cell");
        String phone = intent.getStringExtra("phone");
        String message = intent.getStringExtra("message");

        Log.d("name","Name is "+name+" Email is"+email+" ...");
    }
}
