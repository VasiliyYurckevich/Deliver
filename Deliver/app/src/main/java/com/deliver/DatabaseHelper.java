package com.deliver;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DB_PATH = "/data/data/com.deliver/database"; // полный путь к базе данных
    public static String DB_NAME = "DeliverDB.db";
    public static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "dish"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "dish_name";
    public static final String COLUMN_CALORIE = "calorie";
    public static final String COLUMN_FATS = "fats";
    public static final String COLUMN_CARBO = "carbo";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COAST = "coast";
    public static final String COLUMN_RESTID = "restarant_id";


    private final Context myContext;
    private ArrayList<String> mNamesList;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }


    @Override
    public void onCreate(SQLiteDatabase db) { }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) { }

    void create_db(){

        File file = new File(DB_PATH);
        if (!file.exists()) {
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void getAllNames(ArrayList<String> mNamesList) {
        String selectQuery = "SELECT " + COLUMN_NAME + " FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            mNamesList.add(cursor.getString(cursor.getColumnIndex("dish_name")));
        }
        mNamesList.forEach((n) -> System.out.println(n));
        db.close();
    }
}