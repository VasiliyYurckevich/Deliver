package com.deliver;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public Button eMenu;
    public Button eShop;
    public Button eMc;
    public Button eKfc;
    public Button eBk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        eMenu = findViewById(R.id.menu);

        eMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuCreator.class);
                startActivity(intent);
            }
        });
    }
}