package com.deliver;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deliver.database.DatabasesHelper;

import java.util.ArrayList;

import scala.reflect.reify.phases.Calculate;

public class MenuCreator extends AppCompatActivity {
    private static final String[] REST= { "Mc'Donalds",
            "KFC", "Burger King"};


    private static final String[] MEAlPLAN = { "Keto Diet\n(20/70/10)",
            "Blanced Diet\n(20/30/50)", "High Protein Diet\n(40/30/30)",
            "Mediterranean Diet\n(10/30/60)"};

    ;
    public int restSelId = 0;
    public int MPSelId = 1;
    public int prot = 0;
    public int fat = 0;
    public int carbo = 0;
    public double decprot = 0;
    public double decfat = 0;
    public double deccarb = 0;

    private EditText eCalorie;
    private Spinner spinner;
    private Spinner spinnerMP;
    private DatabaseHelper Db;
    private Button bCBM;

    public int cal = 1200;
    public int[] limits  = new int[4];
    public int[] limits1  = new int[4];

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public int coast ;
    public int[] price = new int[10];
    public int z = 0;

    public int getCoast() {
        return coast;
    }

    public int[] getPrice() {
        return price;
    }

    public String[] getName() {
        return name;
    }

    public String[] name = new String[10];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creator);
        ArrayList<String> mNamesList = new ArrayList<>();
        bCBM = findViewById(R.id.btnCBM);
        spinner = findViewById(R.id.spinner);
        eCalorie = findViewById(R.id.calories);
        spinnerMP = findViewById(R.id.spinnerMP);
        eCalorie.setInputType(InputType.TYPE_CLASS_NUMBER);
        //cal = Integer.valueOf(eCalorie.getText());

        ArrayAdapter<String> adapterRest = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, REST);
        adapterRest.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapterRest);

        ArrayAdapter<String> adapterMP = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,MEAlPLAN);
        adapterMP.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerMP.setAdapter(adapterMP);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                restSelId = position;
                //Toast.makeText(MenuCreator.this,"Position = " + restSelId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerMP.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MenuCreator.this,"Position = " + position, Toast.LENGTH_SHORT).show();
                MPSelId = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                MPSelId = 0;
            }
        });

        bCBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String x;
                 x = eCalorie.getText().toString();
                try {

                    cal = Integer.valueOf(x);
                }catch (NumberFormatException e){
                    cal = 1200;
                };
               // Toast.makeText(MenuCreator.this,"Cal = " + cal, Toast.LENGTH_SHORT).show();;
                switch (MPSelId) {
                    case  (0):
                        CalculatePFC(0.2, 0.7, 0.1);
                        /*decprot = cal * 0.95 * 0.2 / 4;
                        decfat =  cal * 0.95 * 0.7 / 9;
                        deccarb =  cal * 0.95 * 0.1 / 4;*/
                    break;
                    case (1):
                        CalculatePFC(0.2, 0.3, 0.5);
                        /*decprot =  cal * 0.95 * 0.2 / 4;
                        decfat =  cal * 0.95 * 0.3 / 9;
                        deccarb =  cal * 0.95 * 0.5 / 4;*/
                        break;
                    case (2):
                        CalculatePFC(0.4, 0.3, 0.3);
                        /*decprot =  cal * 0.95 * 0.4 / 4;
                        decfat =  cal * 0.95 * 0.3 / 9;
                        deccarb =  cal * 0.95 * 0.3 / 4;*/
                        break;
                    case (3):
                        CalculatePFC(0.1, 0.3, 0.6);
                        /*decprot =  cal * 0.95 * 0.1 / 4;
                        decfat =  cal * 0.95 * 0.3 / 4;
                        deccarb =  cal * 0.95 * 0.6 / 4;*/
                        break;

                }
                prot = (int)decprot;
                fat = (int)decfat;
                carbo = (int)deccarb;
                limits[0] = cal;
                limits[1] = prot;
                limits[2] = fat;
                limits[3] = carbo ;
                limits1[0] = cal+100;
                limits1[1] = prot+100;
                limits1[2] = fat+100;
                limits1[3] = carbo+100;


                Diet diet = new Diet(limits,limits1);
                diet.model(restSelId);
                coast = diet.search(price,name,restSelId);



                Intent intent = new Intent(MenuCreator.this, BalansedMenu.class);
                intent.putExtra("key1",name);
                intent.putExtra("key2",price);
                intent.putExtra("key3",coast);
                startActivity(intent);
                Toast.makeText(MenuCreator.this,"limits = " + limits[0] + "," + limits[1]+ ","+ limits[2]+ ","+ limits[3] + "," + coast, Toast.LENGTH_SHORT).show();
                }
        });


        }

    public void CalculatePFC(double decprot,double decfat,double deccarb){
        this.decprot =  cal * 0.95 * decprot / 4;
        this.decfat =  cal * 0.95 * decfat / 9;
        this.deccarb =  cal * 0.95 * deccarb / 4;

    }

}

