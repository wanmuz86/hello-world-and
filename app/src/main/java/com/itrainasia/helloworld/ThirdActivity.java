package com.itrainasia.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


    }

    public void buttonPressed(View view) {

        Intent intent = new Intent();;
        intent.putExtra("name","Value enter from second pagee");

        setResult(RESULT_OK,intent);
        finish();
    }
}
