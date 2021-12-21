package com.deliver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText eEmail;
    private EditText ePassword;
    private EditText ePassword2;
    public Button eReg;
   // public ProcessDialog processDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eEmail = findViewById(R.id.etMail);
        ePassword = findViewById(R.id.etPass);
        ePassword2 = findViewById(R.id.etPassword2);
        eReg = findViewById(R.id.btnReg);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        eReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();

            }
        });


    }
private void PerforAuth(){
        String email = eEmail.getText().toString();
        String pass = ePassword.getText().toString();
        String confPass = ePassword2.getText().toString();

         if(pass.isEmpty()|| pass.length()<6){
            ePassword.setError("Error");
        }else if(!pass.equals(confPass)){
            ePassword2.setError("Pass not match");
        }else{
            Toast.makeText(this, "Registration", Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sendUserToNextAct();
                        Toast.makeText(Register.this, "Registration Succsesful", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
    private void sendUserToNextAct(){
        Intent intent = new Intent(Register.this,MainActivity.class);
        startActivity(intent);
    }
}

