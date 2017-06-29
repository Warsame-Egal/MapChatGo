package com.example.warsa.MapChatGo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import com.example.warsa.MapChatGo.R;
import com.example.warsa.MapChatGo.helpers.InputValidation;
import com.example.warsa.MapChatGo.model.User;
import com.example.warsa.MapChatGo.sql.DatabaseHelper;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;
    private NestedScrollView nestedScrollView; //display snackbar message
    private TextInputLayout textInputLayoutName; //layout fo name
    private TextInputLayout textInputLayoutEmail; //layout for email
    private TextInputLayout textInputLayoutPassword; //layout for password
    private TextInputLayout textInputLayoutConfirmPassword; //layout for password check
    private TextInputEditText textInputEditTextName; //name
    private TextInputEditText textInputEditTextEmail; //email
    private TextInputEditText textInputEditTextPassword; //password
    private TextInputEditText textInputEditTextConfirmPassword; //password check
    private AppCompatButton appCompatButtonRegister; //register button
    private AppCompatTextView appCompatTextViewLoginLink; //login link
    private InputValidation inputValidation; //object for inputValidation helps class
    private DatabaseHelper databaseHelper; //object for DatabaseHelper sql class
    private User user; //object for User model class
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViewsListenersObjects(); //call method that initializes all objects
    }

    /**
     * This method is to initialize views
     */
    private void initViewsListenersObjects() {
        //setScrollView
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        //set input layouts
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        //set edit text inputs
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        //set listeners
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

        //set objects
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
        intent = new Intent(RegisterActivity.this, LoginActivity.class); //connect this Activity to registerActivity
    }

    /**
     * listens for button click
     * proccesses sqlite data upon registration
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister: //when button is clicked proccess data to sqlite
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink: //when login link is pressed got to login page
                startActivity(intent); //start the activity
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setName(textInputEditTextName.getText().toString().trim()); //get name
            user.setEmail(textInputEditTextEmail.getText().toString().trim()); //get email
            user.setPassword(textInputEditTextPassword.getText().toString().trim()); //get password

            databaseHelper.addUser(user); //add the user object with all info to the databaseHelper object

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show(); //display message on success

            //clear the text fields after user has filled and executed them
            textInputEditTextName.setText(null);
            textInputEditTextEmail.setText(null);
            textInputEditTextPassword.setText(null);
            textInputEditTextConfirmPassword.setText(null);

        } else { //message indicating user alraedy exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }
}
