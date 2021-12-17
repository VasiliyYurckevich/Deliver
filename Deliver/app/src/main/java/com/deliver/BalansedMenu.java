package com.deliver;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BalansedMenu extends AppCompatActivity {

    public TextView TV;
    protected int restSelId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balansed_menu);
        TV = findViewById(R.id.tv);
        Diet diet = new Diet();
        diet.model();
        diet.search(TV);

    }
}