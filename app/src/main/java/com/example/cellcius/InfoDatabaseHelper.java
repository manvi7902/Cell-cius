package com.example.cellcius;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class InfoDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Information.db";
    public static final String TABLE_NAME = "information_user";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="Blood";
    public static final String COL_3 ="Allergies";
    public static final String COL_4 ="Conditions";
    public static final String COL_5 ="Lifestyle";
    public static final String COL_6 ="Checkups";


    public InfoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE information_user (ID INTEGER PRIMARY KEY AUTOINCREMENT, Blood TEXT, Allergies TEXT, Conditions TEXT, Lifestyle TEXT, Checkups TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //ADD NEW USER
    public long newUser(String blood, String allergy, String condi, String life, String checkup){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Blood", blood);
        cv.put("Allergies", allergy);
        cv.put("Conditions", condi);
        cv.put("Lifestyle", life);
        cv.put("Checkups", checkup);
        long result = db.insert("information_user", null, cv);
        db.close();
        return result;
    }

    public String getdata() {

        String[] col = new String[]{COL_1, COL_2, COL_3,COL_4, COL_5, COL_6}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";
        int indexRow = c.getColumnIndex(COL_1);
        int indexName = c.getColumnIndex(COL_2);
        int indexPhone = c.getColumnIndex(COL_3);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexRow) + ": " + c.getString(indexName) + " " + c.getString(indexPhone) + "\n";
        }
        c.close();
        return results;
    }
}
