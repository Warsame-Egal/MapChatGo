package com.example.warsa.MapChatGo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.example.warsa.MapChatGo.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton appCompatButtonLogout;
    private AppCompatButton appCompatButtonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        displaySharedPreferences(); //display preferences
        initViews();
        initListeners();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        appCompatButtonLogout = (AppCompatButton) findViewById(R.id.appCompatLogoutButton);
        appCompatButtonDelete = (AppCompatButton) findViewById(R.id.appCompatDeleteButton);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogout.setOnClickListener(this);
        appCompatButtonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.appCompatLogoutButton:

                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE); // preferences mode
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                //finish();
                // Navigate to RegisterActivity
                Intent intentLogin = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;

            case R.id.appCompatDeleteButton:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    public void displaySharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE); //set preferences
        String display = preferences.getString("display", ""); //display content from preferences

        TextView displayInfo = (TextView) findViewById(R.id.appCompatTextViewEmail); //
        displayInfo.setText(display); //display content
    }


}
