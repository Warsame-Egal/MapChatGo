package com.example.warsa.MapChatGo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.example.warsa.MapChatGo.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void redirect(View view) {
        final Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); //connect this Activity to registerActivity

        TextView text =(TextView) findViewById(R.id.gotoLogin); //get the textview id
        text.setOnClickListener(new OnClickListener() { //create listener for textview

            @Override
            public void onClick(View v) {
                startActivity(intent); //start the activity
            }
        });
    }
}
