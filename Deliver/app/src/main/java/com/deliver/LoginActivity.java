package com.deliver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail;
    private EditText ePassword;
    public Button eLogin;
    public Button eSingUp;


    private boolean isValid = false;
    private int counter = 5;

    private final String incorrData = "Incorrect data entreated! You have attempts left: ";
    private final String corrData = "Correct login and password!";
    private final String emptyData = "Please enter all the details correctly";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = eEmail.getText().toString();
                String inputPass = ePassword.getText().toString();

                if (inputEmail.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, emptyData, Toast.LENGTH_SHORT).show();
                } else {
                    isValid = validate(inputEmail, inputPass);

                    if (!isValid) {

                        counter--;
                        Toast.makeText(LoginActivity.this, incorrData + counter, Toast.LENGTH_SHORT).show();
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, corrData, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });


    }

    private boolean validate(String email, String pass) {
        return email.equals(adminEmail) && pass.equals(adminPass);
    }








































































    private final String adminEmail = "Admin";
    private final String adminPass = "12345678";
}