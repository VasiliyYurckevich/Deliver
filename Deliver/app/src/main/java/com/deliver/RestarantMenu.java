package com.deliver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.deliver.database.DatabasesHelper;

import java.util.Arrays;

public class RestarantMenu extends AppCompatActivity {

    public int rest;
    public String[] dish;
    public int[] cal;
    public int[] cost;
    public double[] cost1;
    public double[] cost2;
    public ScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restarant_menu);
        scroll = findViewById(R.id.scroll);

        DatabasesHelper db = new DatabasesHelper();

        rest = getIntent().getExtras().getInt("restid");
        System.out.println(rest);
        switch (rest){
            case (1):
                dish = db.getFoodMC();
                cal = db.getCalMC();
                cost = db.getPriceMC();
                break;
            case (2):
                dish = db.getFoodKFC();
                cal = db.getCalKFC();
                cost = db.getPriceKFC();
                break;
            case (3):
                dish = db.getFoodBK();
                cal = db.getCalBK();
                cost = db.getPriceBK();
                break;
            default:
                dish = db.getFoodMC();
                cal = db.getCalMC();
                cost = db.getPriceMC();
                break;

        }

        cost1 = Arrays.stream(cost).asDoubleStream().toArray();
        cost1 = Arrays.stream(cost1).map(i -> i/100).toArray();


        int COLUMNS = 3;
        int ROW = dish.length-1;

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        for (int i = 0; i < ROW; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            //tableRow.spa
            for (int j = 0; j < COLUMNS; j++) {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                //textView.setW
                textView.setTextSize(20);

                switch (j) {
                    case (0):
                        textView.setText(dish[i]);

                        textView.setWidth(500);
                        break;
                    case (1):
                        textView.setText(Integer.toString(cal[i]));
                        textView.setWidth(300);

                        break;
                    case (2):
                        textView.setText(Double.toString(cost1[i])+" руб");
                        textView.setWidth(300);
                        break;
                }
                tableRow.addView(textView,j);
            }

            tableLayout.addView(tableRow, i);
    }
        scroll.computeScroll();
}
}