package com.example.warsa.MapChatGo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.warsa.MapChatGo.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void redirect(View view) {
        final Intent intent = new Intent(LoginActivity.this, RegisterActivity.class); //connect this Activity to registerActivity

        TextView text =(TextView) findViewById(R.id.gotoRegister); //get the textview id
        text.setOnClickListener(new View.OnClickListener() { //create listener for textview
            @Override
            public void onClick(View v) {
                startActivity(intent); //start the activity
            }
        });
    }
}
