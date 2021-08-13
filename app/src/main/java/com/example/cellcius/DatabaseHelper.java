package com.example.cellcius;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Register.db";
    public static final String TABLE_NAME = "register_user";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="Name";
    public static final String COL_3 ="Sap_id";
    public static final String COL_4 ="Email";
    public static final String COL_5 ="Phone";
    public static final String COL_6 ="Blood";
    public static final String COL_7 ="Allergies";
    public static final String COL_8 ="Conditions";
    public static final String COL_9 ="Lifestyle";
    public static final String COL_10 ="Checkups";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register_user (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Sap_id TEXT, Email TEXT, Phone TEXT, Blood TEXT, Allergies TEXT, Conditions TEXT, Lifestyle TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //ADD NEW USER
    public long newUser(String name, String sapID, String email, String phone,String blood, String allergy, String condi, String life){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Sap_id", sapID);
        cv.put("Email", email);
        cv.put("Phone", phone);
        cv.put("Blood", blood);
        cv.put("Allergies", allergy);
        cv.put("Conditions", condi);
        cv.put("Lifestyle", life);
        long result = db.insert("register_user", null, cv);
        db.close();
        return result;
    }

    //CHECK FOR USER
    public boolean checkUser(String name, String sapID){
        String[] col= { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" +" and " + COL_3 + "=?";
        String[] selectionArgs = { name, sapID }; //replaces the ?
        Cursor c = db.query(TABLE_NAME, col, selection, selectionArgs,null,null,null);
        int count = c.getCount();
        c.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }
    public String getdata() {

        String[] col = new String[]{COL_1, COL_2, COL_6}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";
        int indexRow = c.getColumnIndex(COL_1);
        int indexName = c.getColumnIndex(COL_2);
        int indexPhone = c.getColumnIndex(COL_6);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexRow) + ": " + c.getString(indexName) + " " + c.getString(indexPhone) + "\n";
        }
        c.close();
        return results;
    }


    public String getName(){
        String[] col = new String[]{COL_2}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";

        int indexName = c.getColumnIndex(COL_2);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexName) + "\n";
        }
        c.close();
        return results;
    }
    public String getSapid(){
        String[] col = new String[]{COL_3}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";

        int indexName = c.getColumnIndex(COL_3);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexName) + "\n";
        }
        c.close();
        return results;
    }
    public String getblood(){
        String[] col = new String[]{COL_6}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";

        int indexName = c.getColumnIndex(COL_6);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexName) + "\n";
        }
        c.close();
        return results;
    }
    public String getallergy(){
        String[] col = new String[]{COL_7}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";

        int indexName = c.getColumnIndex(COL_7);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexName) + "\n";
        }
        c.close();
        return results;
    }
    public String getcondition(){
        String[] col = new String[]{COL_8}; //retrive from database
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, col, null, null, null, null, null);  //we fix the cursor to a column so that it can move t next later on
        //RETERIVING DATA

        String results = "";

        int indexName = c.getColumnIndex(COL_8);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //the cursor is at a paticular row n we are reteriving the data at each column
            results = results + c.getString(indexName) + "\n";
        }
        c.close();
        return results;
    }

}
