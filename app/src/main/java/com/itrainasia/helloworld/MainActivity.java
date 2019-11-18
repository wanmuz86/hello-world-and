package com.itrainasia.helloworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText nameEditText, emailEditText, phoneEditText, cellEditText, messageEditText;
    Button button, anotherButton;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    String[] departments = {"IT","Marketing","HR","Management","Marcom","Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.mainNameEditText);
        emailEditText = findViewById(R.id.mainEmailEditText);
        phoneEditText = findViewById(R.id.phone);
        cellEditText = findViewById(R.id.cell);
        messageEditText = findViewById(R.id.messageEditText);

        button = findViewById(R.id.sendBtn);
        button.setOnClickListener(this);

        anotherButton = findViewById(R.id.sendAnotherBtn);
        anotherButton.setOnClickListener(this);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.cities,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> acAdapter =new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                departments);
        acAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(acAdapter);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sendBtn:
                Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
                Log.d("mainactivity", "Logging " + nameEditText.getText().toString());
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String cell = cellEditText.getText().toString();
                String message = messageEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                intent.putExtra("cell", cell);
                intent.putExtra("message", message);
                startActivity(intent);

                break;
            case R.id.sendAnotherBtn:
                Intent intent2 = new Intent(MainActivity.this, ForthActivity.class);
                startActivityForResult(intent2, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String secondPageData = data.getStringExtra("name");
                Log.d("mainactivity","data sent is "+secondPageData);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.exit:
                finish();
                break;
            case R.id.about_us:
                // Explicit Intent
                Intent webIntent = new Intent(
                        MainActivity.this, WebActivity.class);
                startActivity(webIntent);
                break;

            case R.id.email_us:


                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("text/plain");
                mailIntent.putExtra(Intent.EXTRA_EMAIL,"wanmuz@gmail.com");
               // mailIntent.putExtra(Intent.EXTRA_CC,"test@test.com");
                mailIntent.putExtra(Intent.EXTRA_SUBJECT,"traing feedback");
                mailIntent.putExtra(Intent.EXTRA_TEXT,"body");
//                try {
                    startActivity(mailIntent);
//                }
//                catch (Exception err){
//                    Log.d("debug","Error "+err);
//                }


                break;

            case R.id.call_us:
                // Implicit intent
                // Action to open it
                Uri uri = Uri.parse("tel:60123456789");
                Intent callIntent = new Intent(Intent.ACTION_DIAL,uri);

                startActivity(callIntent);


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Log.d("debug","Item has been selected "+((TextView)view).getText().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void dateSelected(View view) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");

    }

    public void processDatePicker(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);
        Toast.makeText(this, "Date: " + dateMessage,
                Toast.LENGTH_SHORT).show();
    }
}
