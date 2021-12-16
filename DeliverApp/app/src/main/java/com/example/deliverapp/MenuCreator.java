package com.example.deliverapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deliverapp.database.DatabaseHelper;

import java.util.ArrayList;

public class MenuCreator extends AppCompatActivity {

    private static final String[] MEAlPLAN = { " Keto Diet\n(20/70/10)",
            "Blanced Diet\n(20/30/50)", "High Protein Diet\n(40/30/30)",
            "Mediterranean Diet\n(40/30/30)"};

    protected int restSelId = 1;
    protected int MPSelId = 1;
    private EditText eCalorie;
    private Spinner spinner;
    private Spinner spinnerMP;
    private DatabaseHelper Db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creator);
        ArrayList<String> mNamesList = new ArrayList<>();
        Db = new DatabaseHelper(this);
        Db.open();
        Db.getAllNames(mNamesList);
        spinner = findViewById(R.id.spinner);
        eCalorie = findViewById(R.id.calories);
        spinnerMP = findViewById(R.id.spinnerMP);

        ArrayAdapter<String> adapterRest = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mNamesList);
        adapterRest.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapterRest);

        ArrayAdapter<String> adapterMP = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,MEAlPLAN);
        adapterMP.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerMP.setAdapter(adapterMP);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restSelId = position+1;
                Toast.makeText(MenuCreator.this,"Position = " + restSelId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerMP.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}