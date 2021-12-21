package com.deliver;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jacop.constraints.In;

import java.util.Arrays;

public class BalansedMenu extends AppCompatActivity {

    public TextView TV;
    protected int restSelId = 0;
    public int[] limits  = new int[4];
    public int prot = 0;
    public int fat = 0;
    public int carbo = 0;
    public int cal = 0;
    public ListView list1;
    public ListView list2;


    public int[] price ;
    public String[] name;
    public int coast ;
    public double cost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balansed_menu);

       name = getIntent().getExtras().getStringArray("key1");
       price = getIntent().getExtras().getIntArray("key2");
       coast = getIntent().getExtras().getInt("key3");

        TV = findViewById(R.id.tv);
        list1 = findViewById(R.id.list1);
        list2 = findViewById(R.id.list2);
        cost = coast/100;
        TV.setText("Cost = " + cost + " рублей");
        price = Arrays.stream(price).filter(x -> x != 0).toArray();;

        String[] price1 = new String[price.length];

        for (int i = 0; i < price.length; i++) {
            price1[i] = String.valueOf(price[i]);
        }

        name = Arrays.stream(name)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, name);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, price1);

        list1.setAdapter(adapter1);
        list2.setAdapter(adapter2);
    }
}


