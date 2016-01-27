package com.example.skyforlife.projectwithactivity.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.entities.ProfileLab;
import com.example.skyforlife.projectwithactivity.exception.CustomException;
import com.example.skyforlife.projectwithactivity.model.ProfileRecord;
import com.example.skyforlife.projectwithactivity.service.local.LocalConstants;
import com.example.skyforlife.projectwithactivity.service.remote.RequestResponse;
import com.example.skyforlife.projectwithactivity.utils.Logger;
import com.example.skyforlife.projectwithactivity.service.remote.RequestSender;

public class LoginActivity extends AppCompatActivity implements RequestResponse, LocalConstants {
    private static final int SIGNIN = 0;
    private static final int SIGNUP = 1;
    private Button signInButton;
    private Button signUpButton;
    private EditText usernameText;
    private EditText pwdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);

        usernameText = (EditText) findViewById(R.id.input_username);
        pwdText = (EditText) findViewById(R.id.input_password);
        signInButton = (Button) findViewById(R.id.sign_in_button);
        signUpButton = (Button) findViewById(R.id.sign_up_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(SIGNIN);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (usernameText.getText().toString().isEmpty() ||
                            pwdText.getText().toString().isEmpty()) {
                        throw new CustomException("Username or password cannot be empty!",
                                CustomException.ExceptionType.INVALID_INPUT);
                    }
                    sendRequest(SIGNUP);
                } catch (CustomException e) {
                    Toast.makeText(LoginActivity.this, e.toString(),
                            Toast.LENGTH_SHORT).show();
                    e.fix();
                }
            }
        });
    }

    @Override
    public void processFinish(String result) {
        if (result == null || !result.equals("success")) {
            Toast.makeText(LoginActivity.this, "Request failed.", Toast.LENGTH_LONG).show();
            return;
        }
        Logger.log(result, 0);
        // login success
        if (ProfileLab.get(this).readProfile() == null) {
            // create profile if not exists
            ProfileLab.get(this).createProfile(new ProfileRecord());
        }
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void sendRequest(int login) {
        try {
            String reqStr = String.format(LOGIN_QUERY, DNS, usernameText.getText().toString(),
                    pwdText.getText().toString(), login);
            RequestSender sender = new RequestSender(LoginActivity.this);
            sender.delegate = LoginActivity.this;
            sender.execute(reqStr);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
            Toast.makeText(LoginActivity.this,
                    "Invalid input.", Toast.LENGTH_SHORT).show();
        }
    }
}
