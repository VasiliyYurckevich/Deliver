package com.deliver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail;
    private EditText ePassword;
    public Button eLogin;
    public Button eSingUp;


    FirebaseAuth mAuth;
    FirebaseUser mUser;


    private boolean isValid = false;
    private int counter = 5;

    private final String incorrData = "Incorrect data entreated! You have attempts left: ";
    private final String corrData = "Correct login and password!";
    private final String emptyData = "Please enter all the details correctly";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eSingUp = findViewById(R.id.btnSignIn);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        eSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        });


    }

  /*  private boolean validate(String email, String pass) {
        return email.equals(adminEmail) && pass.equals(adminPass);
    }*/
    private void perforLogin(){
    String email = eEmail.getText().toString();
    String pass = ePassword.getText().toString();
    //String confPass = ePassword2.getText().toString();

         if(pass.isEmpty()|| pass.length()<6){
            ePassword.setError("Error");
        }else {
                Toast.makeText(this, "Registration", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             sendUserToNextAct();
                             Toast.makeText(LoginActivity.this, "login Succsesful", Toast.LENGTH_SHORT).show();

                         }else {
                             Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                         }
                    }

                });
            }
}
    private void sendUserToNextAct(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

}





































































        ///private final String adminEmail = "Admin";
 //   private final String adminPass = "12345678";
